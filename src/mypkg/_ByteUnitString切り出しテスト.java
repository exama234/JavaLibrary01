package mypkg;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class _ByteUnitString切り出しテスト {
	
	@Test
	public void 文字の切り出し_半角() {
		// 半角文字列が切り出し（substring）できること。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("abc");
		
		expect = "abc";
		actual = obj.toString();
		assertThat(actual, is(expect));
		
		expect = "abc";
		actual = obj.substring(0, 3).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "ab";
		actual = obj.substring(0, 2).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "a";
		actual = obj.substring(0, 1).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.substring(0, 0).trimOddByte().toString();
		assertThat(actual, is(expect));
	}
		
	@Test
	public void 文字列の復元() {
		// 文字列の切り出し（substring）後、復元（restore）で文字列が復活すること。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいう";
		actual = obj.substring(0, 9).toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえお";
		actual = obj.restore().toString();
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 文字列を後方切り出し_全角() {
		// 全角文字列が切り出し（substring）できること。
		// ※前方部分の切り出し。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.substring(0, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "いうえお";
		actual = obj.restore().substring(1, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "いうえお";
		actual = obj.restore().substring(2, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "いうえお";
		actual = obj.restore().substring(3, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "うえお";
		actual = obj.restore().substring(4, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "うえお";
		actual = obj.restore().substring(5, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "うえお";
		actual = obj.restore().substring(6, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "えお";
		actual = obj.restore().substring(7, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "えお";
		actual = obj.restore().substring(8, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "えお";
		actual = obj.restore().substring(9, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "お";
		actual = obj.restore().substring(10, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "お";
		actual = obj.restore().substring(11, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "お";
		actual = obj.restore().substring(12, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(13, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(14, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		try {
			obj.restore().substring(15, 15).trimOddByte().toString();
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		
		// 「あいうえお」の0バイト目～9バイト目を取得。
		// ※第２引数でキャラクタセットを指定可能。デフォルト「UTF-8」
		ByteUnitString obj2 = new ByteUnitString("かきくけこ");
		System.out.println(obj2.toString());
		obj2 = obj2.substring(0, 10);
		System.out.println(obj2.substring(0, 10));
		
		// 前方後方の半端なバイトを削除する。
		// ※この時点で「あいう?」→「あいう」となる。
		obj2.trimOddByte();
		System.out.println(obj2.toString());
		
		// 「あいう」の9バイトが結果として返る。
		String data1 = obj2.toString();
		System.out.println(data1);
	}
	
	@Test
	public void 文字列を前方切り出し_全角_トリム処理なし() {
		// 半角文字列が切り出し（substring）できること。
		// ※後方部分の切り出し。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえお";
		actual = obj.substring(0, 15).toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.substring(0, 12).toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.substring(0, 9).toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.substring(0, 6).toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.substring(0, 3).toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.substring(0, 0).toString();
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 文字列を前方切り出し_全角_トリム処理あり() {
		// 半角文字列が切り出し（substring）できること。
		// ※後方部分の切り出し。
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.substring(0, 15).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.restore().substring(0, 14).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.restore().substring(0, 13).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいうえ";
		actual = obj.restore().substring(0, 12).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.restore().substring(0, 11).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.restore().substring(0, 10).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あいう";
		actual = obj.restore().substring(0, 9).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.restore().substring(0, 8).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.restore().substring(0, 7).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あい";
		actual = obj.restore().substring(0, 6).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.restore().substring(0, 5).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.restore().substring(0, 4).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "あ";
		actual = obj.restore().substring(0, 3).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(0, 2).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(0, 1).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		expect = "";
		actual = obj.restore().substring(0, 0).trimOddByte().toString();
		assertThat(actual, is(expect));
	}
	
	@Test
	public void 文字列を前後方切り出し_全角_トリム処理あり_その１() {
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "あいうえお";
		actual = obj.toString();
		assertThat(actual, is(expect));
		
		expect = "いう";
		actual = obj.substring(3, 9).trimOddByte().toString();
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 文字列を前後方切り出し_全角_トリム処理あり_その２() {
		String actual = null;
		String expect = null;
		ByteUnitString obj = new ByteUnitString("あいうえお");
		
		expect = "いうえ";
		obj = obj.substring(2, 12);// ?いうえ
		actual = obj.substring(1, 10).toString();
		assertThat(actual, is(expect));
		
		expect = "う";
		actual = obj.substring(3, 8).trimOddByte().toString();
		assertThat(actual, is(expect));
		
		obj.substring(0, 10);
		try {
			obj.substring(0, 11);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生テストが成功しました。
		}
	}

	@Test
	public void 文字の切り出し_エラー() {
		// 切り出し（substring）のインデックス指定ミスで例外が発生すること。
		ByteUnitString obj = new ByteUnitString("abc");
		
		try {
			obj.substring(0, -1).trimOddByte().toString();
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			obj.substring(0, 4).trimOddByte().toString();
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}
		
	@Test
	public void 空文字の切り出しで例外発生() {
		// 空文字の切り出し（substring）で例外が発生すること。
		ByteUnitString obj = new ByteUnitString("");
		
		try {
			ByteUnitString.isProperIndex(obj.toString(), -1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			assertFalse(ByteUnitString.isProperIndex("", 0));
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			ByteUnitString.isProperIndex(obj.toString(), 1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}

}
