import Navbar from "./components/customer/navbar/Navbar";
import Home from "./pages/Home";

function App() {
  return (
    <>
      <h1 className="text-danger">Tasty Town</h1>
      <Navbar />

      <Home/>
    </>
  );
}

export default App;