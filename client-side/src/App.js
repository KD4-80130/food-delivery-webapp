import { CssBaseline, ThemeProvider } from "@mui/material";
import Navbar from "./components/Navbar";
import { lightTheme } from "./theme/LightTheme";

function App() {
  return (
    <ThemeProvider theme={lightTheme}>
      <CssBaseline/>
      <Navbar/>
    </ThemeProvider>
     
  );
}

export default App;
