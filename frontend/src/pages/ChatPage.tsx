import { useEffect, useMemo, useState } from "react";
import { Button, Stack, TextField, Typography } from "@mui/material";
import { useParams } from "react-router-dom";
import { api } from "../api/client";
import { useAuth } from "../context/AuthContext";
import type { Message } from "../types/models";
import { createStompClient } from "../ws/stompClient";

export function ChatPage() {
  const { instrumentId, otherUserId } = useParams();
  const { session } = useAuth();
  const [messages, setMessages] = useState<Message[]>([]);
  const [content, setContent] = useState("");

  const topic = useMemo(() => {
    if (!session || !otherUserId || !instrumentId) return "";
    const a = Math.min(session.userId, Number(otherUserId));
    const b = Math.max(session.userId, Number(otherUserId));
    return `/topic/chat/${instrumentId}/${a}_${b}`;
  }, [session, otherUserId, instrumentId]);

  useEffect(() => {
    if (!session || !instrumentId || !otherUserId) return;
    api.get<Message[]>(`/messages/${instrumentId}/${otherUserId}`).then((r) => setMessages(r.data));
    const client = createStompClient();
    client.onConnect = () => client.subscribe(topic, (msg) => setMessages((prev) => [...prev, JSON.parse(msg.body)]));
    client.activate();
    return () => { void client.deactivate(); };
  }, [session, instrumentId, otherUserId, topic]);

  if (!session) return <Typography>Please log in</Typography>;

  return (
    <Stack spacing={2}>
      <Typography variant="h5">Chat</Typography>
      <Stack spacing={1}>
        {messages.map((m) => <Typography key={m.id} align={m.senderId === session.userId ? "right" : "left"}>{m.content}</Typography>)}
      </Stack>
      <TextField value={content} onChange={(e) => setContent(e.target.value)} placeholder="Type a message..." />
      <Button variant="contained" onClick={() => {
        const payload = { senderId: session.userId, receiverId: Number(otherUserId), instrumentId: Number(instrumentId), content };
        const client = createStompClient();
        client.onConnect = () => { client.publish({ destination: "/app/chat.send", body: JSON.stringify(payload) }); client.deactivate(); };
        client.activate();
        setContent("");
      }}>Send</Button>
    </Stack>
  );
}
