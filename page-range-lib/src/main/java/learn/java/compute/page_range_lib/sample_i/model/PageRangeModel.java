package learn.java.compute.page_range_lib.sample_i.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageRangeModel {

  private String pageRange;

  public PageRangeModel() {
    this.pageRange = "1-1";
  }

  public void setPageRange( String pageRange ) {
    if( !pageRange.isBlank() )
      this.pageRange = pageRange;
  }

  public String getPageRange() {
    return this.pageRange;
  }

  public List<Short[]> parsePageRanges() {
    List<Short[]> resultRanges = new ArrayList<>(9);
    String[] srcRangeStrParts = this.pageRange.split(",");

    for( String srcRangeStrPart : srcRangeStrParts ) {
      final String[] srcRawRangeStrParts = srcRangeStrPart.split("-");

      try {
        if( srcRawRangeStrParts.length == 1 ) {
          final short val = Short.parseShort(srcRawRangeStrParts[0]);
          resultRanges.add(
            new Short[]{ val, val }
          );
        }
        else if( srcRawRangeStrParts.length == 2 && !srcRawRangeStrParts[0].isBlank() ) {
          final short start = Short.parseShort(srcRawRangeStrParts[0]);
          final short end = Short.parseShort(srcRawRangeStrParts[1]);
          if( start > end ) throw new RuntimeException(
            String.format("Invalid Number ranges parsing: %d-%d", start, end)
          );
          resultRanges.add(
            new Short[]{ start, end }
          );
        }
      }
      catch( final RuntimeException uncheckException ) {
        uncheckException.printStackTrace(System.err);
        System.err.printf( "Error: %s \r\n", uncheckException.getLocalizedMessage() );
        throw uncheckException;
      }
    }

    return resultRanges;
  }

  public String sortAndFormatRanges(int totalNumPages) {
    List<Short[]> ranges = this.parsePageRanges();

    //REM: Flatten and validate
    List<Short> allPages = new ArrayList<>();

    for (Short[] range : ranges) {
      for (Short i = range[0]; i <= range[1]; i++) {
        if (i > 0 && i <= totalNumPages)
          allPages.add(i);
      }
    }

//    Collections.<Short>sort(allPages);

    allPages = allPages.stream()
      .distinct()
      .sorted()
      .collect( Collectors.<Short>toList() );

    //REM: Reformat ranges
    StringBuilder result = new StringBuilder();
    Short start = -1, end = -1;
    for (Short page : allPages) {
      if (start == -1) {
        start = page;
        end = page;
      } else if (page == end + 1) {
        end = page;
      } else {
        if (result.length() > 0) {
          result.append(",");
        }
        result.append(start == end ? String.valueOf(start) : start + "-" + end);
        start = page;
        end = page;
      }
    }
    if (start != -1) {
      if (result.length() > 0) {
        result.append(",");
      }
      result.append(start == end ? String.valueOf(start) : start + "-" + end);
    }

    return result.toString();
  }
}