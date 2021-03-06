public class MultiDefaultIterator<T> extends DefaultIterator<T>
{
  private T[] defaultValues;
  private int indexDefaultValue;
  
  public MultiDefaultIterator(T[] array, T[] defaultValues)
  {
    super(array, null);
    this.defaultValues = defaultValues;
    this.indexDefaultValue = -1;
  }
  
  @Override
  public T next()
  {
    T result = super.next();
    
    if (result == null)
    {
      indexDefaultValue = (indexDefaultValue + 1) % defaultValues.length;
      result = defaultValues[indexDefaultValue];
    }
    
    return result;
  }
}