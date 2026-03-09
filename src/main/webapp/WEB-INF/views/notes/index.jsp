<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Notes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .header {
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 30px;
        }
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        .menu-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 20px;
            text-align: center;
            transition: transform 0.3s;
        }
        .menu-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
        }
        .menu-card h3 {
            color: #2c3e50;
            margin-bottom: 15px;
        }
        .menu-card p {
            color: #7f8c8d;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #2980b9;
        }
        .btn-primary {
            background-color: #27ae60;
        }
        .btn-primary:hover {
            background-color: #229954;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>${titre}</h1>
            <p>${sousTitre}</p>
        </div>
        
        <div class="menu-grid">
            <div class="menu-card">
                <h3>Insérer une Note</h3>
                <p>Ajouter une nouvelle note pour un étudiant, une matière et un professeur</p>
                <a href="${pageContext.request.contextPath}/notes/inserer" class="btn">Insérer une Note</a>
            </div>
            
            <div class="menu-card">
                <h3>Consulter une Note</h3>
                <p>Calculer et afficher la note réelle d'un étudiant pour une matière selon les règles</p>
                <a href="${pageContext.request.contextPath}/notes/consulter" class="btn btn-primary">Consulter une Note</a>
            </div>
        </div>
        
        <div style="margin-top: 40px; text-align: center; color: #7f8c8d;">
            <p>Règles de calcul : Les notes sont calculées selon des paramètres définis par matière</p>
        </div>
    </div>
</body>
</html>