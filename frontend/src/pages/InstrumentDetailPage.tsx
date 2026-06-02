import { useEffect, useState } from "react";
import { Button, Stack, TextField, Typography } from "@mui/material";
import { Link, useParams } from "react-router-dom";
import { api } from "../api/client";
import type { Instrument } from "../types/models";
import { useAuth } from "../context/AuthContext";

export function InstrumentDetailPage() {
  const { id } = useParams();
  const { session } = useAuth();
  const [instrument, setInstrument] = useState<Instrument | null>(null);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  useEffect(() => { api.get<Instrument>(`/instruments/${id}`).then((r) => setInstrument(r.data)); }, [id]);

  if (!instrument) return <Typography>Loading...</Typography>;

  return (
    <Stack spacing={2}>
      <Typography variant="h4">{instrument.title}</Typography>
      <Typography>{instrument.description}</Typography>
      <Typography>{instrument.manufacturer} {instrument.model}</Typography>
      <Typography>€{instrument.dailyRate}/day - {instrument.city}, {instrument.country}</Typography>
      {session && session.userId !== instrument.ownerId && (
        <>
          <TextField type="date" label="Start date" InputLabelProps={{ shrink: true }} value={startDate} onChange={(e) => setStartDate(e.target.value)} />
          <TextField type="date" label="End date" InputLabelProps={{ shrink: true }} value={endDate} onChange={(e) => setEndDate(e.target.value)} />
          <Button variant="contained" onClick={() => api.post("/bookings", { instrumentId: instrument.id, startDate, endDate })}>Book now</Button>
          <Button component={Link} to={`/chat/${instrument.id}/${instrument.ownerId}`}>Chat with owner</Button>
        </>
      )}
    </Stack>
  );
}
