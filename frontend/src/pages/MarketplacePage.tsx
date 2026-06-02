import { useEffect, useState } from "react";
import { Card, CardContent, Typography, Button, CardActions, Stack } from "@mui/material";
import { Link } from "react-router-dom";
import { api } from "../api/client";
import type { Instrument } from "../types/models";

export function MarketplacePage() {
  const [instruments, setInstruments] = useState<Instrument[]>([]);
  useEffect(() => { api.get<Instrument[]>("/instruments").then((r) => setInstruments(r.data)); }, []);

  return (
    <Stack spacing={2}>
      {instruments.map((instrument) => (
        <div key={instrument.id}>
          <Card>
            <CardContent>
              <Typography variant="h6">{instrument.title}</Typography>
              <Typography color="text.secondary">{instrument.manufacturer} {instrument.model}</Typography>
              <Typography>{instrument.city}, {instrument.country}</Typography>
              <Typography sx={{ mt: 1 }}>€{instrument.dailyRate}/day</Typography>
            </CardContent>
            <CardActions>
              <Button component={Link} to={`/instrument/${instrument.id}`}>View</Button>
            </CardActions>
          </Card>
        </div>
      ))}
    </Stack>
  );
}
