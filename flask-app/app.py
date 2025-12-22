from flask import Flask, render_template, request, redirect, url_for
from werkzeug.utils import secure_filename
import os
app = Flask(__name__)

# ----------------------------------------------------------------------
# إعدادات التطبيق والملفات
# ----------------------------------------------------------------------
# 1. المجلد الذي سيتم حفظ الصور فيه (static/)

UPLOAD_FOLDER = 'static'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
# 2. أنواع الملفات المسموح بها (يمكن تعديلها حسب الحاجة)
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}
# 3. الحل لخطأ SERVER_NAME: تعيين اسم الخادم لبناء الروابط خارج الطلب
app.config['SERVER_NAME'] = 'localhost:5000'
# ----------------------------------------------------------------------

# ----------------------------------------------------------------------
# البيانات الأساسية (Mock Database)
# ----------------------------------------------------------------------
# نستخدم 'image_filename' بدلاً من 'image_url' لتجنب خطأ السياق
PRODUCTS_BASE_DATA = [
    {'id': 1, 'name': 'هاتف ذكي X (إصدار محدود)', 'price': 799.99,
     'details': 'أقوى هاتف لعام 2025. يتميز بكاميرا 200MP ومعالج A15.',
     'image_filename': 'default.png'},
    {'id': 2, 'name': 'سماعات بلوتوث Pro', 'price': 149.00,
     'details': 'جودة صوت عالية وإلغاء ضوضاء فعال لمدة 30 ساعة متواصلة.',
     'image_filename': 'default.png'}
]

products_data = [] # القائمة النهائية التي تحتوي على الروابط
next_id = 3

# دالة لتهيئة المنتجات بالروابط (تُستدعى في سياق التطبيق)
def initialize_products_data():
    global products_data, next_id
    products_data.clear()

    for p in PRODUCTS_BASE_DATA:
        p_with_url = p.copy()
        p_with_url['image_url'] = url_for('static', filename=p['image_filename'])
        products_data.append(p_with_url)

        if PRODUCTS_BASE_DATA:
           max_id = 0
           for p in PRODUCTS_BASE_DATA:
             if p['id'] > max_id:
                 max_id = p['id']
                 next_id = max_id + 1
             else:
                  next_id = 1

# تهيئة البيانات في سياق التطبيق قبل التشغيل
with app.app_context():
    initialize_products_data()

# ----------------------------------------------------------------------
# تعريف المسارات (Routes)
# ----------------------------------------------------------------------

@app.route('/')
@app.route('/products')
def product_list():
    return render_template('index.html', products=products_data)

@app.route('/products/<int:product_id>')
def product_detail(product_id):
    product = next((p for p in products_data if p['id'] == product_id), None)
    if product is None:
        return "Product Not Found"
    return render_template('product_detail.html', product=product)

@app.route('/add', methods=['GET', 'POST'])
def add_product():
    global next_id
    if request.method == 'POST':
        image_filename = 'default.jpg' # القيمة الافتراضية

        # 1. معالجة تحميل الصورة
        if 'image_file' in request.files:
            file = request.files['image_file']
            if file :
                # تأمين اسم الملف وحفظه
                filename = secure_filename(file.filename)
                file.save(os.path.join(UPLOAD_FOLDER, filename))
                image_filename = filename

        # 2. حفظ البيانات الجديدة
        new_product_base = {
            'id': next_id,
            'name': request.form['name'],
            'price': float(request.form['price']),
            'details': request.form['details'],
            'image_filename': image_filename
        }

        # إنشاء رابط url_for للمنتج الجديد (آمن لأنه ضمن سياق الطلب)
        new_product = new_product_base.copy()
        new_product['image_url'] = url_for('static', filename=new_product['image_filename'])

        products_data.append(new_product)
        next_id += 1
        return redirect(url_for('product_list'))

    return render_template('add_product.html')      
@app.route('/delete/<int:product_id>', methods=['POST'])
def delete_product(product_id):
    global products_data
    # 1. البحث عن المنتج الذي سيتم حذفه للحصول على اسم ملفه
    product_to_delete = next((p for p in products_data if p['id'] == product_id), None)

    if product_to_delete:
        filename_to_delete = product_to_delete.get('image_filename')

        # 2. حذف الملف من القرص (إذا لم يكن الملف الافتراضي)
        if filename_to_delete and filename_to_delete != 'default.png':
            file_path = os.path.join(UPLOAD_FOLDER, filename_to_delete)

            # التحقق من وجود الملف قبل محاولة حذفه
            if os.path.exists(file_path):
                os.remove(file_path)
                print(f"File deleted successfully: {file_path}") # لغرض التتبع
            else:
                print(f"File not found: {file_path}")

        # 3. حذف المنتج من قائمة البيانات (الذاكرة)
        products_data = [p for p in products_data if p['id'] != product_id]

    return redirect(url_for('product_list'))
if __name__ == '__main__':
    app.run(debug=True)