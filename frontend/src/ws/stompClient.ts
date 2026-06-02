import { Client } from "@stomp/stompjs";

export function createStompClient() {
  return new Client({
    brokerURL: "ws://localhost:8080/ws/chat",
    reconnectDelay: 5000,
  });
}
