import { useQuery } from '@tanstack/react-query';
import { getGuitar } from './api/guitars';

export default function HomePage() {
  const guitarId = "1";

  const { data, isLoading, isError } = useGetGuitar("1");

  const message = isLoading
    ? "Loading..."
    : isError
    ? "Error fetching message"
    : data.model;

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>

      <h1>Vite + React</h1>

      <div className="card">
        <button onClick={() => {}}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>

      <p className="read-the-docs">{message}</p>
    </>
  );
}
