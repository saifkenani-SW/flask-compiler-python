{% css %}
.title {
  color: red;
  font-size: 20px;
}
{% endcss %}

<div>
  Hello {{ user }}

  {% set age = 20 %}

  {% if age >= 18 %}
    <p>Adult</p>
  {% endif %}
</div>
