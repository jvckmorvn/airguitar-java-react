import { Box, Typography } from "@mui/material";

export default function Footer() {
    return (
        <Box
                component="footer"
                sx={{ py: 2, textAlign: "center", bgcolor: "background.paper" }}
              >
                <Typography variant="caption">© 2025 My App</Typography>
              </Box>
        );
    }
