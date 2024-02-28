## <h4 align="center"> Интерполирование функции с помощью многочленов Эрмита по m точкам, в которых заданы значения функции и производных. </h4>

<div align="center">
<h1>
Постановка задачи  
</h1>
<p>
Входные параметры:
</p>

* X – вектор значений аргументов в порядке возрастания (вектор узлов интерполяции);
* Y – вектор значений функции в узлах интерполяции;
* DY – вектор значений производной функции в узлах интерполяции;
* N – количество узлов интерполяции, в которых заданы значения функций;
* XX – значение аргумента, при котором будет вычисляться интерполяционное значение функции;
* m – количество точек, по которым строится многочлен Эрмита.
<p>
Выходные параметры:
</p>

* YY – вычисленное интерполяционное значение функции в точке XX ;

> Метод. Вычисляется значение интерполяционного многочлена Эрмита в точке XX по значениям функции и её производных в точках, наименее удалённых от точки XX .

<h1>
Алгоритм 
</h1>
</div>

1. Инициализация:
- Входные параметры: массивы
> X
> Y
> DY
>  значение XX
> количество ближайших точек m.

- Переменные: YY  (результат интерполяции), closestPoints (ближайшие точки к  XX ).

2. Нахождение ближайших точек:
   - Для каждого элемента массива X  находим разницу с  XX  и сортируем пары по этой разнице.
   - Выбираем  m  пар с наименьшей разницей как ближайшие точки.

3. Вычисление интерполяционного значения  YY :
   - Для каждой ближайшей точки:
     1. Находим индекс выбранной точки  idx .
     2. Вычисляем многочлен  L :
     
          ![image](https://github.com/NIOHOMY/Numerical_Methods_6_semester/assets/38347892/7f907afd-60ff-4730-8a6b-017a67ce7c0d)

   - Вычисляем значение  H :

       ![image](https://github.com/NIOHOMY/Numerical_Methods_6_semester/assets/38347892/242e156b-6362-48f1-8773-7ab665097f8c)

     
4. Вычисляем значение a , учитывая условие :
   
     ![image](https://github.com/NIOHOMY/Numerical_Methods_6_semester/assets/38347892/603db224-ab93-464d-991a-2bdb4123a651)

6. Вычисляем значение b :
   
      ![image](https://github.com/NIOHOMY/Numerical_Methods_6_semester/assets/38347892/bb1d5322-8596-408a-8bcb-c9425fdc0f7a)

7. Обновляем YY с учетом текущей точки:
   
      ![image](https://github.com/NIOHOMY/Numerical_Methods_6_semester/assets/38347892/e80e1f43-f738-40ba-9faa-ef304f296983)

8. Возвращение результата:
   - Возвращаем значение YY  как результат интерполяции в точке XX .

      ![image](https://github.com/NIOHOMY/Numerical_Methods_6_semester/assets/38347892/7e1e625f-f612-4703-b27d-e97e0b755135)

* Примеры 1-7 показывают, что при использовании функции hermiteInterpolation с заданными значениями X, Y, DY, XX и m, программа для многочлена степени 2 и 4 вычисляет значение этого многочлена в точке XX даже если m, количество точек, меньше степени+1 многочлена.

* Примеры 8-10 демонстрируют работу функции hermiteInterpolation и её точность в зависимости от удалённости точек, этими тестами мы так же показываем, что строится многочлен по ближайшим к XX узлам таблицы при любом взаимном расположении узлов и точки XX
* Примеры 11-13 показывают, что действительно строится многочлен степени n и на выход подаётся значение этого многочлена в точке XX

