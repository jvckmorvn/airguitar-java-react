import { createContext, useContext, useMemo, useState } from "react";
import type { ReactNode } from "react";
import { api } from "../api/client";
import type { UserSession } from "../types/models";

type AuthContextType = {
  session: UserSession | null;
  login: (email: string, password: string) => Promise<void>;
  register: (name: string, email: string, password: string) => Promise<void>;
  logout: () => void;
};

const AuthContext = createContext<AuthContextType | undefined>(undefined);

function loadSession(): UserSession | null {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const name = localStorage.getItem("name");
  const email = localStorage.getItem("email");
  return token && userId && name && email ? { token, userId: Number(userId), name, email } : null;
}

export function AuthProvider({ children }: { children: ReactNode }) {
  const [session, setSession] = useState<UserSession | null>(loadSession());

  const persist = (payload: UserSession) => {
    localStorage.setItem("token", payload.token);
    localStorage.setItem("userId", String(payload.userId));
    localStorage.setItem("name", payload.name);
    localStorage.setItem("email", payload.email);
    setSession(payload);
  };

  const value = useMemo<AuthContextType>(
    () => ({
      session,
      login: async (email, password) => {
        const { data } = await api.post<UserSession>("/auth/login", { email, password });
        persist(data);
      },
      register: async (name, email, password) => {
        const { data } = await api.post<UserSession>("/auth/register", { name, email, password });
        persist(data);
      },
      logout: () => {
        localStorage.clear();
        setSession(null);
      },
    }),
    [session],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("AuthContext missing");
  return ctx;
}
