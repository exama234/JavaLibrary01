package mypkg;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class _ByteUnitStringキャラクタセット指定のテスト {
	
	@Test
	public void キャラクタセット指定のテスト() {
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお", "Shift_JIS");
		
		expect = "あいうえお";
		actual = obj.toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.substring(0, 4).toString();
		assertThat(actual, is(expect));
	}
	
}
