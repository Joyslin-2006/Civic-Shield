import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";

import Navbar from "./components/Navbar";

import Dashboard from "./pages/Dashboard";
import RaiseIssue from "./pages/RaiseIssue";
import Workers from "./pages/Workers";
import Assignments from "./pages/Assignments";
import Login from "./pages/Login";
import Register from "./pages/Register";


function Layout() {

    const location = useLocation();

    const hideNavbar =
        location.pathname === "/" ||
        location.pathname === "/register";

    return (
        <>
            {!hideNavbar && <Navbar />}

            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/register" element={<Register />} />

                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/issue" element={<RaiseIssue />} />
                <Route path="/workers" element={<Workers />} />
                <Route path="/assignments" element={<Assignments />} />
            </Routes>
        </>
    );
}

function App() {
    return (
        <BrowserRouter>
            <Layout />
        </BrowserRouter>
    );
}

export default App;