package learn.java.compute.page_range_lib.test.sample_i.model;

import learn.java.compute.page_range_lib.sample_i.model.PageRangeModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class TestPageRangeModel {

  @Test
  public void testInit() {
    PageRangeModel pRModel = new PageRangeModel();

    pRModel.setPageRange("-1,5,1,8-91,0,8,13,7,-1,-3,0,65,91");

    System.out.println(pRModel.getPageRange());
    pRModel.parsePageRanges().forEach((v)->{
      System.out.println(Arrays.toString(v));
    });
    System.out.println(pRModel.sortAndFormatRanges(91));
  }
}
