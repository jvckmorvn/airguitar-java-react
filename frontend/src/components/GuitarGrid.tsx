import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import GuitarCard from './GuitarCard';

export default function GuitarGrid() {
    const guitarIds = ['1'];

    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={3}>
                {guitarIds.map((guitarId: string) => (
                    <GuitarCard key={guitarId} guitarId={guitarId} />
                ))}
            </Grid>
        </Box>
    );
}
