import { useState } from "react";
import { issueApi } from "../services/api";

function RaiseIssue() {

    const [issue, setIssue] = useState({
        title: "",
        description: "",
        issueType: "ROAD",
        location: "",
        pincode: "",
        urgency: "HIGH"
    });

    const handleChange = (e) => {
        setIssue({
            ...issue,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await issueApi.post("/issues", issue);

            alert("Issue Created!");

            console.log(response.data);

        } catch (error) {

            console.log(error);
            console.log(error.response);
            console.log(error.response?.data);
            alert("Failed to create issue");

        }
    };

    return (
        <div style={{ padding: "20px" }}>

            <h2>Raise Issue</h2>

            <form onSubmit={handleSubmit}>

                <input
                    name="title"
                    placeholder="Title"
                    onChange={handleChange}
                />

                <br /><br />

                <input
                    name="description"
                    placeholder="Description"
                    onChange={handleChange}
                />

                <br /><br />

                <input
                    name="location"
                    placeholder="Location"
                    onChange={handleChange}
                />

                <br /><br />

                <input
                    name="pincode"
                    placeholder="Pincode"
                    onChange={handleChange}
                />

                <br /><br />

                <select name="issueType" onChange={handleChange}>
                    <option>ROAD</option>
                    <option>STREETLIGHT</option>
                    <option>DRAINAGE</option>
                    <option>GENERAL</option>
                </select>

                <br /><br />

                <select name="urgency" onChange={handleChange}>
                    <option>HIGH</option>
                    <option>MEDIUM</option>
                    <option>LOW</option>
                </select>

                <br /><br />

                <button type="submit">
                    Submit
                </button>

            </form>

        </div>
    );
}

export default RaiseIssue;