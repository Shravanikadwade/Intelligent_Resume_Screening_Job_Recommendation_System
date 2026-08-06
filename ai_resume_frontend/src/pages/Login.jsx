import { useState } from "react";
import { loginUser } from "../services/authService";



const Login = () => {

    const [form, setForm] = useState({
        email: "",
        password: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    }

    const handleSubmit = async (e) => {

    e.preventDefault();

    try {

        const response = await loginUser(form);

        console.log(response.data);

    } catch (error) {

        console.log(error);

    }

};



    return (
        <div className="login-page">

            <h2>Login</h2>

            <form onSubmit={handleSubmit}>

                <div>
                    <label>Email</label>
                    <br />
                    <input
                        type="email"
                        placeholder="Enter your email"
                        name="email"
                        value={form.email}
                        onChange={handleChange}
                    />
                </div>

                <br />

                <div>
                    <label>Password</label>
                    <br />
                    <input
                        type="password"
                        placeholder="Enter password"
                        name="password"
                        value={form.password}
                        onChange={handleChange}
                    />
                </div>

                <br />

                <button type="submit">
                    Login
                </button>

            </form>

        </div>
    );
};

export default Login;