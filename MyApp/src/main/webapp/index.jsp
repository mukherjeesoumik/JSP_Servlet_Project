<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap');

        body {
            font-family: 'Montserrat', sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        .container {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            text-align: left;
            margin-bottom: 5px;
            font-weight: 600;
            color: #555;
        }

        input, select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
            transition: border-color 0.3s;
        }

        input:focus, select:focus {
            border-color: #9b59b6;
        }

        button {
            padding: 12px;
            border: none;
            border-radius: 6px;
            background-color: #28a745; /* Green color */
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218838; /* Darker green on hover */
        }

        .mobile-input-group {
            display: flex;
            gap: 5px;
        }

        .country-code {
            width: 90px;
        }

        .mobile-number {
            flex-grow: 1;
        }

        .gender-group {
            display: flex;
            gap: 10px;
            align-items: center;
        }

        .gender-group label {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Registration Form</h1>
    <form action="helloservlet" method="POST">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required>

        <label for="gender">Gender:</label>
        <div class="gender-group">
            <input type="radio" id="male" name="gender" value="male" required>
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="female" required>
            <label for="female">Female</label>
        </div>

        <label for="mobilenumber">Mobile Number:</label>
        <div class="mobile-input-group">
            <select class="country-code" id="countrycode" name="countrycode" required>
                <option value="+91">IN +91</option>
                <option value="+1">US +1</option>
                <option value="+44">UK +44</option>
                <option value="+61">AU +61</option>
            </select>
            <input type="tel" class="mobile-number" id="mobilenumber" name="mobilenumber"  required>
        </div>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
