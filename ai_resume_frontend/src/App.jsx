import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Resume from "./pages/Resume";
import Jobs from "./pages/Jobs";
import Admin from "./pages/Admin";
import Navbar from "./components/Navbar";

import Footer from "./components/Footer";
import Layout from "./components/Layout";

function App() {
  return (

    <>
    <center>
    <Layout>

    <Routes>

      <Route path="/" element={<Login />} />

      <Route path="/register" element={<Register />} />

      <Route path="/dashboard" element={<Dashboard />} />

      <Route path="/resume" element={<Resume />} />

      <Route path="/jobs" element={<Jobs />} />

      <Route path="/admin" element={<Admin />} />

    </Routes>

   </Layout>
    </center>
    </>

  );
}

export default App;