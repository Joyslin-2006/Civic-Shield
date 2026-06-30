import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Register() {
    const navigate = useNavigate();
    const [user, setUser] = useState({
        name: "",
        email: "",
        password: "",
        role: "CITIZEN"
    });

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const register = async (e) => {

        e.preventDefault();

        try {

            await axios.post(
                "http://localhost:8081/auth/register",
                user
            );

            alert("Registration Successful!");
            navigate("/");
        } catch (err) {

            alert("Registration Failed");

        }

    };

    return (

        <div style={{padding:"20px"}}>

            <h2>Register</h2>

            <form onSubmit={register}>

                <input
                    name="name"
                    placeholder="Name"
                    onChange={handleChange}
                />

                <br/><br/>

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

                <select
                    name="role"
                    onChange={handleChange}
                >
                    <option>CITIZEN</option>
                    <option>ADMIN</option>
                </select>

                <br/><br/>

                <button type="submit">

                    Register

                </button>

            </form>

        </div>

    );

}

export default Register;