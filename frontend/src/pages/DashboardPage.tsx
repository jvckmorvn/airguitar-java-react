import { useEffect, useState } from "react";
import { Divider, List, ListItem, ListItemText, Stack, Typography } from "@mui/material";
import { api } from "../api/client";
import type { Booking, Notification } from "../types/models";

export function DashboardPage() {
  const [bookings, setBookings] = useState<Booking[]>([]);
  const [notifications, setNotifications] = useState<Notification[]>([]);

  useEffect(() => {
    api.get<Booking[]>("/bookings/my").then((r) => setBookings(r.data));
    api.get<Notification[]>("/notifications/my").then((r) => setNotifications(r.data));
  }, []);

  return (
    <Stack spacing={3}>
      <div>
        <Typography variant="h5">My bookings</Typography>
        <List>{bookings.map((b) => <ListItem key={b.id}><ListItemText primary={`Instrument #${b.instrumentId}`} secondary={`${b.startDate} - ${b.endDate} (€${b.totalPrice})`} /></ListItem>)}</List>
      </div>
      <Divider />
      <div>
        <Typography variant="h5">Recent notifications</Typography>
        <List>{notifications.map((n) => <ListItem key={n.id}><ListItemText primary={n.title} secondary={n.body} /></ListItem>)}</List>
      </div>
    </Stack>
  );
}
