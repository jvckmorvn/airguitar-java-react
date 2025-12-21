import { useQuery } from '@tanstack/react-query';
import { getGuitar } from '../api/guitars';

export function useGetGuitars(guitarIds: string[]) {
  return useQuery({
    queryKey: ["guitars", guitarIds],
    queryFn: () => getGuitars(guitarIds),
  });
}

export function useGetGuitar(guitarId: string) {
  return useQuery({
    queryKey: ["guitar", guitarId],
    queryFn: () => getGuitar(guitarId),
  });
}
