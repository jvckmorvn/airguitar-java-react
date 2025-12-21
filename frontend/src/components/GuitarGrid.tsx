import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import GuitarCard from './GuitarCard';
import { useGetGuitars } from '../hooks/useGuitars';

export default function GuitarGrid() {
  const guitarIds = ["1"];
  const { data, isLoading } = useGetGuitars(guitarIds);

  if (isLoading || !data) return null;

  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={3}>
        {data.map((guitar) => (
          <Grid item key={guitar.id}>
            <GuitarCard guitar={guitar} />
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
