package Helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Helper
{

    /**
     * Finds first Element satisfying p
     * @param l a list
     * @param p a predicate
     * @return the first element s in list l such that s satisfies p 
     */
    public static <S> S find(List<S> l, Predicate<S> p)
    {
        Iterator<S> it = l.iterator();
        while(it.hasNext())
        {
            S s = it.next();
            if(p.test(s))
        	return s;
        }
        throw new NoSuchElementException();
    }

    /**
     * Deletes the first element satisfying p
     * @param l a list
     * @param p a predicate
     * @return true if there was an element satisfying p, false otherwise
     */
    public static <S> boolean delete(List<S> l, Predicate<S> p)
    {
        Iterator<S> it = l.iterator();
        while(it.hasNext())
        {
            S s = it.next();
            if(p.test(s))
            {
        	it.remove();
        	return true;
            }
        }
        return false;
    }

    public static <S, W> List<W> map(List<S> list, Function<S, W> f)
    {
        Iterator<S> it = list.iterator();
        List<W> listW = new ArrayList<>(); 
        while(it.hasNext())
            listW.add(f.apply(it.next()));
        return listW;
    }

    public static Integer add(Integer a, Integer b)
    {
        return (a == null || b == null) ? null : a + b;
    }

    public static int compare(Integer a, Integer b)
    {
        if(a == null && b == null)
            return 0;
        if(a == null)
            return 1;
        if(b == null)
            return -1;
        return a.compareTo(b);
    }

}
