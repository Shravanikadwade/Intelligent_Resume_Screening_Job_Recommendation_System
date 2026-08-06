import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar">

      <h2 className="logo">
        AI Resume System
      </h2>

      <div className="nav-links"> {" | "}

        <Link to="/">Login</Link> {" | "}

        <Link to="/register">Register</Link>{" | "}

        <Link to="/dashboard">Dashboard</Link>{" | "}

        <Link to="/jobs">Jobs</Link>{" | "}

        <Link to="/resume">Resume</Link>{" | "}

      </div>

    </nav>
  );
}

export default Navbar;