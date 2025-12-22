import axios from 'axios';
import ENDPOINTS from '../util/endpoints';
import type { Guitar } from '../types/Guitar';

export async function getGuitars(guitarIds: string[]): Promise<Guitar[]> {
  const { data } = await axios.get(ENDPOINTS.GET_GUITARS, {
    params: { ids: guitarIds }
  });

  return data;
}

export async function getGuitar(guitarId: string): Promise<Guitar> {
  const { data } = await axios.get(ENDPOINTS.GET_GUITAR(guitarId));
  return data;
}
