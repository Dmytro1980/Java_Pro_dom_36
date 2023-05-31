<h1 align="center">Hillel Online</h1>
<h2>Java Pro</h2>

<h3>Task 36. Links</h3>
<ol>
<li>Create app  (back-end), for store links in DB.

    Entities:

    Tag:
    - id *
    - name *
    - createdAt *

    Category:
    - id *
    - name *
    - createdAt *

    Link:
    - id *
    - name *
    - value * (trhe link) https://lms.ithillel.ua/groups/...
    - categoryId *
    - tags (many to many: table link_tag (link_id, tag_id))
    - created_at *

    Black list:
    - id *
    - domain * -> lms.ithillel.ua
    - createdAt *

<li>API:

    1.Get all tags, search by name, create, update, delete tags.
    2.Getting all categories, search by name, create, update, delete, deleting categories.  
    3.Getting all links, getting all links with pagination,
      getting by name and/or link and/or category and/or tag, create, update, delete  links.
    4.Get all , create, update, delete  to/fromBlack List.

</ol>

========================================

<h3>ДЗ 36. Links</h3>
<ol>
<li>Разработать приложение (back-end), которое позволяет хранить ссылки в БД.

    Сущности:

    Тэг:
    - id *
    - name *
    - createdAt *

    Категория:
    - id *
    - name *
    - createdAt *

    Ссылка:
    - id *
    - name *
    - value * (сама ссылка) https://lms.ithillel.ua/groups/...
    - categoryId *
    - tags (many to many: table link_tag (link_id, tag_id))
    - created_at *

    Черный список:
    - id *
    - domain * -> lms.ithillel.ua
    - createdAt *

<li>API:

    1.Получение всех, поиск по имени, создание, обновление, удаление тегов.
    2.Получение всех, поиск по имени, создание, обновление, удаление категорий  
    3.Получение всех, получение всех с пагинацией,
      получение по имени и/или ссылке и/или категории и/или тэгам, создание, обновление, удаление ссылок
    4.Получение всех, создание, обновление, удаление в/из черного списка

</ol>