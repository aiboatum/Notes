#   list 
list may constructed in several ways:
1. using a list comprehension:
   ```python
   l=[x for x in iterable]
   ```
2. using the type constructor:
   ```python
   list(iterable) (especially,list())
   ```
we should take much count into the following:
1. `list(iterabel) return a copy of iterable if iterable is a list`
2. `slice operations`

**some demos**
```python
squares=[]
for x in range(10):
    squares.append(x**2)

# equivalent to the above 
squares=[x**2 for x in range(10)]
# equivalent to the above in some sense
squraes=list(map(lambda x:x**2 ,range(10)))

```