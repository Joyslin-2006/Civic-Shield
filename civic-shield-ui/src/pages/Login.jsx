import { useState } from "react";
import { authApi } from "../services/api";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

function Login() {

    const navigate = useNavigate();

    const [login, setLogin] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {

        setLogin({
            ...login,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response = await authApi.post(
                "/login",
                login
            );

            localStorage.setItem(
                "token",
                response.data.token
            );

            alert("Login Successful");

            navigate("/dashboard");
        } catch (error) {

            alert("Invalid Credentials");

        }

    };

    return (

        <div style={{padding:"20px"}}>

            <h2>Login</h2>

            <form onSubmit={handleSubmit}>

                <input
                    name="email"
                    placeholder="Email"
                    onChange={handleChange}
                />

                <br/><br/>

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    onChange={handleChange}
                />

                <br/><br/>

                <button>

                    Login

                </button>
                <br /><br />

                <p>
                    Don't have an account? <Link to="/register">Register</Link>
                </p>

            </form>

        </div>

    );

}

export default Login;