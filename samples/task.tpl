<!DOCTYPE html>
<html lang="ar" dir="rtl">
{% extends "base.html" %}
{% block title %}إضافة منتج جديد{% endblock %}

{% block content %}
<h2>إضافة منتج جديد</h2>

<form method="POST" action="{{ url_for('add_product') }}" class="product-form" enctype="multipart/form-data">

    <label for="name">اسم المنتج:</label>
    <input type="text" id="name" name="name" required>

    <label for="price">سعر المنتج ($):</label>
    <input type="number" id="price" name="price" step="0.01" min="0.01" required>

    <label for="details">التفاصيل / الوصف:</label>
    <textarea id="details" name="details" rows="5" required></textarea>

    <label for="image">صورة المنتج:</label>
    <input type="file" id="image" name="image_file" accept=".jpg, .jpeg, .png, .gif" required>

    <button type="submit" class="submit-button">حفظ وإضافة المنتج</button>
</form>
{% endblock %}
</html>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>{% block title %}تطبيق المنتجات{% endblock %}</title>

    <style class="h" media="all">
body {
    font-family: 'Arial';
    background-color: #f4f4f4;
    color: #333;
    margin: 0;
    padding: 0;
    text-align: right;
    direction: rtl;
}

header {
    background-color: #007bff;
    color: white;
    padding: 20px;
    text-align: center;
}

.product-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}
    </style>
</head>

<body>
<header>
    <h1>متجر التجارة الإلكترونية المصغر</h1>
    <nav>
        <a href="{{ url_for('product_list') }}">عرض المنتجات</a>
        <a href="{{ url_for('add_product') }}">إضافة منتج</a>
    </nav>
</header>

<main>
    {% block content %}
    <p>محتوى افتراضي.</p>
    {% endblock %}
</main>

<footer>
    <p>&copy; 2025 Flask & Jinja2 App</p>
</footer>
</body>
</html>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
{% extends "base.html" %}

{% block title %}قائمة المنتجات{% endblock %}

{% block content %}
<h2>المنتجات المتوفرة حالياً</h2>

{% for product in products %}
<div class="product-card">
    <h3>{{ product.name }}</h3>
    <p>{{ product.price }} $</p>
    <a href="{{ url_for('product_detail', product_id=product.id) }}">تفاصيل</a>
</div>
{% endfor %}

{% if not products %}
<p>لا توجد منتجات</p>
{% endif %}
{% endblock %}
</html>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
{% extends "base.html" %}
{% block title %}تفاصيل المنتج{% endblock %}

{% block content %}
<h2>{{ product.name }}</h2>
<p>{{ product.details }}</p>
<p>{{ product.price }} $</p>
{% endblock %}
</html>
