import { useGetGuitar } from '../hooks/useGuitar';

export default function HomePage() {
  const guitarId = "1";
  const { data, isLoading, isError } = useGetGuitar(guitarId);

  let message;
  if (isLoading) message = 'Loading...';
  if (isError) message = 'Error fetching message';
  if (data) message = data.model;

  return (
    <>
      <h1>Vite + React</h1>

      <div className="card">
        <p>
          {message}
        </p>
      </div>
    </>
  );
}
