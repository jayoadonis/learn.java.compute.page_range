package learn.java.compute.page_range_lib.test.sample_i.model;

import learn.java.compute.page_range_lib.sample_i.model.PageRangeModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class TestPageRangeModel {

  @Test
  public void testSetAndGetPageRange() {
    PageRangeModel pRModel = new PageRangeModel();

    String inputRange = "-1,5,1,8-91,0,8,13,7,-1,-3,0,65,91";
    pRModel.setPageRange(inputRange);

    Assertions.assertEquals(inputRange, pRModel.getPageRange(), "Page range should match the input.");
  }

  @Test
  public void testParsePageRanges() {
    PageRangeModel pRModel = new PageRangeModel();
    pRModel.setPageRange("1,5,8-10,12,15-18");

    List<Short[]> parsedRanges = pRModel.parsePageRanges();

    Assertions.assertEquals(5, parsedRanges.size(), "Expected 4 ranges to be parsed.");
    Assertions.assertArrayEquals(new Short[]{1,1}, parsedRanges.get(0), "First range mismatch.");
    Assertions.assertArrayEquals(new Short[]{5,5}, parsedRanges.get(1), "Second range mismatch.");
    Assertions.assertArrayEquals(new Short[]{8, 10}, parsedRanges.get(2), "Third range mismatch.");
    Assertions.assertArrayEquals(new Short[]{12,12}, parsedRanges.get(3), "4th range mismatch.");
    Assertions.assertArrayEquals(new Short[]{15,18}, parsedRanges.get(4), "5th range mismatch.");
  }

  @Test
  public void testSortAndFormatRanges() {
    PageRangeModel pRModel = new PageRangeModel();
    pRModel.setPageRange("8-10,1,15-18,5,12");

    String formattedRanges = pRModel.sortAndFormatRanges(20);

    String expectedOutput = "1,5,8-10,12,15-18";
    Assertions.assertEquals(expectedOutput, formattedRanges, "Formatted range output mismatch.");
  }

//  @Test
//  public void testInvalidInput() {
//    PageRangeModel pRModel = new PageRangeModel();
//    pRModel.setPageRange("-1,abc,5,8-10,,12-15");
//
//    List<Short[]> parsedRanges = pRModel.parsePageRanges();
//
//    Assertions.assertEquals(3, parsedRanges.size(), "Expected only valid ranges to be parsed.");
//    Assertions.assertArrayEquals(new Short[]{5,5}, parsedRanges.get(0), "First valid range mismatch.");
//    Assertions.assertArrayEquals(new Short[]{8,10}, parsedRanges.get(1), "Second valid range mismatch.");
//    Assertions.assertArrayEquals(new Short[]{12,15}, parsedRanges.get(2), "Third valid range mismatch.");
//  }

  @Test
  public void testParsePageRangesNonNumeric() {
    PageRangeModel pRModel = new PageRangeModel();
    pRModel.setPageRange("-1,abc,5,8-10,,12-15");

    Assertions.assertThrowsExactly(
      NumberFormatException.class,
      pRModel::parsePageRanges
    );
  }

  @Test
  public void tryInvalidRangesInvalidRange() {
    PageRangeModel pRModel = new PageRangeModel();
    pRModel.setPageRange("-1,5,8-10,12-15,20-16");

    Assertions.assertThrowsExactly(
      RuntimeException.class,
      pRModel::parsePageRanges
    );
  }
}

