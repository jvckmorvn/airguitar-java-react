import axios from 'axios';
import ENDPOINTS from '../util/endpoints';

export async function getGuitar(guitarId: string): Promise<Guitar> {
  const { data } = await axios.get(ENDPOINTS.GET_GUITAR(guitarId));
  return data;
}
