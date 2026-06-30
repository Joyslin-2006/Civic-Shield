import { useEffect, useState } from "react";
import { workerApi } from "../services/api";

function Assignments() {

    const [assignments, setAssignments] = useState([]);

    useEffect(() => {
        fetchAssignments();
    }, []);

    const fetchAssignments = async () => {

        try {

            const response = await workerApi.get("/assignments");

            setAssignments(response.data);

        } catch (error) {

            console.log(error);

        }

    };

    return (

        <div style={{ padding: "20px" }}>

            <h2>Assignments</h2>

            <table border="1" cellPadding="10">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Issue ID</th>
                    <th>Worker ID</th>
                    <th>Status</th>

                </tr>

                </thead>

                <tbody>

                {assignments.map(assignment => (

                    <tr key={assignment.id}>

                        <td>{assignment.id}</td>

                        <td>{assignment.issueId}</td>

                        <td>{assignment.workerId}</td>

                        <td>{assignment.status}</td>

                    </tr>

                ))}

                </tbody>

            </table>

        </div>

    );

}

export default Assignments;