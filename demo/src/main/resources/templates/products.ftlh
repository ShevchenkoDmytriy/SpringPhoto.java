<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Rozetka</title>
</head>
<body>


<h1>Products</h1>

<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price}грн. | <a href="/product/${product.id}">Подробнее...</a> </p>
    </div>
</#list>


</body>
</html>



