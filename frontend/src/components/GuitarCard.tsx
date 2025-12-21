import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import CardActionArea from '@mui/material/CardActionArea';
import CardActions from '@mui/material/CardActions';
import { useGetGuitar } from '../hooks/useGuitar';

interface GuitarCardProps {
  guitarId: string;
}

export default function GuitarCard({ guitarId }: GuitarCardProps) {
  const { data } = useGetGuitar(guitarId);

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardActionArea>
        <CardMedia
          component="img"
          height="140"
          image="/static/images/cards/contemplative-reptile.jpg"
          alt="guitar"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {`${data?.manufacturer} ${data?.model}` ?? 'Loading...'}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary">
          Share
        </Button>
      </CardActions>
    </Card>
  );
}
