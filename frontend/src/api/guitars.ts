export async function getGuitar(guitarId: string) {
  const { data } = await axios.get(ENDPOINTS.GET_GUITAR(guitarId));
  return data;
}
