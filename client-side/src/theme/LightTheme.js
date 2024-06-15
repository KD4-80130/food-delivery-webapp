import { createTheme } from "@mui/material";

export const lightTheme = createTheme({
  palette: {
    mode: "dark",
    primary: {
      main: "#e91e63",
    },
    secondary: {
      main: "#5A20CB",
    },
    background: {
      default: "#D7FCD4",  
      paper: "#0D0D0D",    
    },
    text: {
      primary: "#ffffff",  
      secondary: "#b0b0b0",  
    },
  },
});
