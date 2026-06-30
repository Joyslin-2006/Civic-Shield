import { Link } from "react-router-dom";

function Navbar() {
    return (
        <nav
            style={{
                background: "#2563eb",
                padding: "15px",
                display: "flex",
                gap: "20px"
            }}
        >
            <Link to="/dashboard" style={{ color: "white" }}>
                Dashboard
            </Link>

            <Link to="/issue" style={{ color: "white" }}>
                Raise Issue
            </Link>

            <Link to="/workers" style={{ color: "white" }}>
                Workers
            </Link>

            <Link to="/assignments" style={{ color: "white" }}>
                Assignments
            </Link>
        </nav>
    );
}

export default Navbar;