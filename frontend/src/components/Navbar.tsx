import { AppBar, Badge, Box, Button, IconButton, Menu, MenuItem, Toolbar, Typography } from "@mui/material";
import NotificationsIcon from "@mui/icons-material/Notifications";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { api } from "../api/client";
import { createStompClient } from "../ws/stompClient";
import type { Notification } from "../types/models";

export function Navbar() {
  const { session, logout } = useAuth();
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);

  useEffect(() => {
    if (!session) return;
    api.get<Notification[]>("/notifications/my").then((r) => setNotifications(r.data));
    const client = createStompClient();
    client.onConnect = () => {
      client.subscribe(`/topic/users/${session.userId}/notifications`, (msg) => {
        setNotifications((prev) => [JSON.parse(msg.body), ...prev]);
      });
    };
    client.activate();
    return () => { void client.deactivate(); };
  }, [session]);

  const unreadCount = notifications.filter((n) => !n.read).length;

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography component={Link} to="/" variant="h6" sx={{ textDecoration: "none", color: "inherit", flexGrow: 1 }}>
          Airguitar
        </Typography>
        <Button component={Link} to="/dashboard" color="inherit">Dashboard</Button>
        {session ? (
          <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
            <IconButton color="inherit" onClick={(e) => setAnchorEl(e.currentTarget)}>
              <Badge badgeContent={unreadCount} color="error"><NotificationsIcon /></Badge>
            </IconButton>
            <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={() => setAnchorEl(null)}>
              {notifications.length === 0 && <MenuItem>No notifications</MenuItem>}
              {notifications.map((n) => (
                <MenuItem key={n.id} onClick={async () => { await api.put(`/notifications/${n.id}/read`); setNotifications((p) => p.map((x) => (x.id === n.id ? { ...x, read: true } : x))); }}>
                  {n.title}
                </MenuItem>
              ))}
            </Menu>
            <Typography>{session.name}</Typography>
            <Button color="inherit" onClick={logout}>Logout</Button>
          </Box>
        ) : (
          <>
            <Button component={Link} to="/login" color="inherit">Login</Button>
            <Button component={Link} to="/register" color="inherit">Register</Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
}
