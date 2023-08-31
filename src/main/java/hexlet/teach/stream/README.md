## Stream and FP  

---
1. what is fp
2. what is stream 
3. main HOR
4. predicates, consumers and functions

функциональное программрование - это программирование с математическими функциями.  

f(x) = x ^ 2
f(10) = 100

стримы - доступное АПИ, которое позволяет писать код на джаве в функциональном стиле

Профиты
1. удобочитаемость
2. производительность


функции высшего порядка - функция которая на вход принимает другую функцию.  
Коллбэк - это функция, которая является аргументом другой функции

ФВП:  
1. map         -  `Stream<A> -> Stream<B>`       inputsize = outputsize 
2. filter      -  `Stream<A> -> Stream<A>`       inputsize >= outputsize
3. reduce      -  `Stream<A> -> Stream<B>`       inputsize == outputsize || inputsize != outputsize
4. forEach     -  `Stream<A> -> void`

## DZ 

показать редьюс , когда inputsize = outputsize 

показать примеры производительности

придумать проблемы, которую бы решали коллективно