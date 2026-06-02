import { Route, Routes } from "react-router-dom";
import { Container } from "@mui/material";
import { Navbar } from "./components/Navbar";
import { MarketplacePage } from "./pages/MarketplacePage";
import { InstrumentDetailPage } from "./pages/InstrumentDetailPage";
import { LoginPage } from "./pages/LoginPage";
import { RegisterPage } from "./pages/RegisterPage";
import { DashboardPage } from "./pages/DashboardPage";
import { ChatPage } from "./pages/ChatPage";

export default function App() {
  return (
    <>
      <Navbar />
      <Container sx={{ py: 3 }}>
        <Routes>
          <Route path="/" element={<MarketplacePage />} />
          <Route path="/instrument/:id" element={<InstrumentDetailPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/chat/:instrumentId/:otherUserId" element={<ChatPage />} />
        </Routes>
      </Container>
    </>
  );
}
