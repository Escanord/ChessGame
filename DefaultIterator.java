import java.util.Iterator;
import java.util.NoSuchElementException;

public class DefaultIterator<T> implements Iterator<T>
{
  private T[] array;
  private T defaultValue;
  private int index;
  
  public DefaultIterator(T[] array, T defaultValue)
  {
    this.array = array;
    this.defaultValue = defaultValue;
    this.index = 0;
  }
  
  public T getDefaultValue()
  {
    return this.defaultValue;
  }
  
  public void setDefaultValue(T defaultValue)
  {
    this.defaultValue = defaultValue;
  }
  
  public boolean hasNext()
  {
    return this.index < this.array.length;
  }
  
  public T next() throws NoSuchElementException
  {
    if (!hasNext()) throw new NoSuchElementException();
    
    T save = array[index];
    ++index;
    
    if (save != null)
      return save;
    else 
      return getDefaultValue();
  }
}