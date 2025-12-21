import { useQuery } from '@tanstack/react-query';
import { getGuitar } from '../api/guitars';

export function useGetGuitar(guitarId: string) {
  return useQuery({
    queryKey: ["guitar", guitarId],
    queryFn: () => getGuitar(guitarId),
  });
}
