import { Outlet } from "react-router-dom";
import { Box } from "@mui/material";
import Header from './Header';
import Footer from './Footer';

export default function Layout() {
  return (
    <Box
      sx={{
        width: "100vw",
        height: "100vh",
        display: "flex",
        flexDirection: "column",
      }}
    >

      <Header />

      <Box sx={{ flexGrow: 1, padding: 3 }}>
        <Outlet />
      </Box>

      <Footer />

    </Box>
  );
}
