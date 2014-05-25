package mypkg;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class _ByteUnitString半端バイトのテスト {
	
	@Test
	public void 切り出しと半端バイトトリムのテスト() {
		// 文字列の切り出し（substring）後、半端なバイトをトリム（trimUnfinishedByte）できること。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえお";
		actual = obj.substring(0, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.substring(0, 14).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あいうえ";
		actual = obj.substring(0, 13).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あいうえ";
		actual = obj.substring(0, 12).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あいう";
		actual = obj.substring(0, 11).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あいう";
		actual = obj.substring(0, 10).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あいう";
		actual = obj.substring(0, 9).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あい";
		actual = obj.substring(0, 8).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あい";
		actual = obj.substring(0, 7).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あい";
		actual = obj.substring(0, 6).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あ";
		actual = obj.substring(0, 5).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あ";
		actual = obj.substring(0, 4).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "あ";
		actual = obj.substring(0, 3).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "";
		actual = obj.substring(0, 2).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "";
		actual = obj.substring(0, 1).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj = new ByteUnitString("あいうえお");
		expect = "";
		actual = obj.substring(0, 0).trimOddByte().toString();
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 左トリム_全角() {
		// 切り出し（substring）による半端なバイトをトリム（trimLeftUnfinishedByte）できること。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.substring(0, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "いうえお";
		actual = obj.restore().substring(1, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "いうえお";
		actual = obj.restore().substring(2, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "いうえお";
		actual = obj.restore().substring(3, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "うえお";
		actual = obj.restore().substring(4, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "うえお";
		actual = obj.restore().substring(5, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "うえお";
		actual = obj.restore().substring(6, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "えお";
		actual = obj.restore().substring(7, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "えお";
		actual = obj.restore().substring(8, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "えお";
		actual = obj.restore().substring(9, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "お";
		actual = obj.restore().substring(10, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "お";
		actual = obj.restore().substring(11, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "お";
		actual = obj.restore().substring(12, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(13, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(14, 15).trimLeftOddByte().toString();
		assertThat(actual, is(expect));
	}
	
	@Test
	public void 右トリム_全角() {
		// 切り出し（substring）による半端なバイトをトリム（trimRightUnfinishedByte）できること。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.substring(0, 15).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.restore().substring(0, 14).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.restore().substring(0, 13).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.restore().substring(0, 12).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.restore().substring(0, 11).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.restore().substring(0, 10).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.restore().substring(0, 9).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.restore().substring(0, 8).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.restore().substring(0, 7).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.restore().substring(0, 6).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.restore().substring(0, 5).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.restore().substring(0, 4).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.restore().substring(0, 3).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(0, 2).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(0, 1).trimRightOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(0, 0).trimRightOddByte().toString();
		assertThat(actual, is(expect));
	}

}
