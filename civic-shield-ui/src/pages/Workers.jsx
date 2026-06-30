import { useEffect, useState } from "react";
import { workerApi } from "../services/api";

function Workers() {

    const [workers, setWorkers] = useState([]);

    useEffect(() => {

        fetchWorkers();

    }, []);

    const fetchWorkers = async () => {

        try {

            const response = await workerApi.get("/workers");

            setWorkers(response.data);

        } catch (error) {

            console.log(error);

        }

    };

    return (

        <div style={{ padding: "20px" }}>

            <h2>Workers</h2>

            <table border="1" cellPadding="10">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Pincode</th>
                    <th>Available</th>

                </tr>

                </thead>

                <tbody>

                {workers.map(worker => (

                    <tr key={worker.id}>

                        <td>{worker.id}</td>

                        <td>{worker.name}</td>

                        <td>{worker.department}</td>

                        <td>{worker.pincode}</td>

                        <td>
                            {worker.available ? "✅ Yes" : "❌ No"}
                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

        </div>

    );

}

export default Workers;