package mypkg;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public class ByteUnitString implements Cloneable {
	
	/** キャラクタセット */
	private final Charset charset;
	/** 初期データ */
	private final String original;
	/** データ */
	private String value = null;
	/** 開始（絶対）インデックス */
	private int startIndex = -1;
	/** バイト長 */
	private int length = -1;
	
	/**
	 * 指定の文字列で ByteUnitString オブジェクトを構築します。
	 * @param string 構築対象の文字列。
	 * @throws NullPointerException 指定された string が null である場合 。
	 */
	public ByteUnitString(String string) throws NullPointerException {
		// コンストラクタ呼び出し。（デフォルトキャラクタセット）
		this(string, Charset.defaultCharset());
	}
	
	/**
	 * 指定の文字列、キャラクタセット名で ByteUnitString オブジェクトを構築します。
	 * @param string 構築対象の文字列。
	 * @param charsetName 文字列のキャラクタセット名。
	 * @throws NullPointerException 指定された string が null である場合。 
	 * @throws IllegalCharsetNameException 指定された文字セットが不正である場合
	 * @throws IllegalArgumentException 指定された charsetName が null である場合 
	 * @throws UnsupportedCharsetException 指定文字セットが現在の Java 仮想マシンでは利用できない場合。
	 */
	public ByteUnitString(String string, String charsetName)
		throws NullPointerException, IllegalCharsetNameException, IllegalArgumentException, UnsupportedCharsetException {
		// コンストラクタ呼び出し。（引数指定キャラクタセット）
		this(string, Charset.forName(charsetName));
	}
	
	/**
	 * 指定の文字列、キャラクタセットで ByteUnitString オブジェクトを構築します。
	 * @param string 構築対象の文字列。
	 * @param charset 文字列のキャラクタセット。
	 * @throws NullPointerException 指定された string が null である場合。 
	 */
	public ByteUnitString(String string, Charset charset) throws NullPointerException {
		if (string == null) {
			throw new NullPointerException();
		}
		this.original = string;
		this.value = string;
		this.charset = charset;
		this.startIndex = 0;
		this.length = string.getBytes(this.charset).length;
	}
	
	/**
	 * きりがいいインデックスかを判定する。<br/>
	 * マルチバイト文字を含む文字列のインデックスを指定した場合、そのインデックスが
	 * マルチバイト文字の１バイト目以外を差していないかを返す。
	 * @param byteIndex 文字列中のインデックス。
	 * @return 引数インデックスのきりがいいかの真偽値。（true：きりがいい／false：悪い）
	 * @throws IndexOutOfBoundsException インデックス範囲外の際に発生する例外。
	 */
	private boolean isProperIndex(int byteIndex) throws IndexOutOfBoundsException {
		return isProperIndex(this.original, byteIndex, this.charset);
	}
	
	/**
	 * きりがいいインデックスかを判定する。<br/>
	 * マルチバイト文字を含む文字列のインデックスを指定した場合、そのインデックスが
	 * マルチバイト文字の１バイト目以外を差していないかを返す。
	 * @param target 対象となる文字列。
	 * @param byteIndex 文字列中のインデックス。
	 * @return 引数インデックスのきりがいいかの真偽値。（true：きりがいい／false：悪い）
	 * @throws IndexOutOfBoundsException インデックス範囲外の際に発生する例外。
	 */
	public static boolean isProperIndex(String target, int byteIndex) throws IndexOutOfBoundsException {
		return isProperIndex(target, byteIndex, Charset.defaultCharset());
	}
	/**
	 * きりがいいインデックスかを判定する。<br/>
	 * マルチバイト文字を含む文字列のインデックスを指定した場合、そのインデックスが
	 * マルチバイト文字の１バイト目以外を差していないかを返す。
	 * @param target 対象となる文字列。
	 * @param byteIndex 文字列中のインデックス。
	 * @param charset キャラクタセット。
	 * @return 引数インデックスのきりがいいかの真偽値。（true：きりがいい／false：悪い）
	 * @throws IndexOutOfBoundsException インデックス範囲外の際に発生する例外。
	 */
	public static boolean isProperIndex(String target, int byteIndex, Charset charset) throws IndexOutOfBoundsException {
		if (target == null) {
			throw new IllegalArgumentException();
		}
		int targetLength = target.getBytes(charset).length;
		if (byteIndex < 0 || targetLength <= byteIndex ) {
			throw new IndexOutOfBoundsException();
		}
		
		String tmp = null;
		int index1 = 0;
		int index2 = target.length() - 1;
		int currentIndex = -1;
		while (true) {
			if (index1 > index2) {
				break;
			}
			
			currentIndex = (index1 + index2) / 2;
			if (currentIndex == index1) {
				// インデックスの変化なし。（２つのインデックスが重複しているか、隣り合っている場合）
				
				// ２つのインデックスを確認。
				tmp = target.substring(0, index1);
				int byteLen = tmp.getBytes(charset).length;
				if (byteIndex == byteLen) {
					// きりがいい。
					return true;
				}
				tmp = target.substring(0, index2);
				byteLen = tmp.getBytes(charset).length;
				if (byteIndex == byteLen) {
					// きりがいい。
					return true;
				}
				break;
			}
			
			tmp = target.substring(0, currentIndex);
			int byteLen = tmp.getBytes(charset).length;
			if (byteIndex == byteLen) {
				return true;
			}
			if (byteLen < byteIndex) {
				// 中間点より後にある。
				index1 = currentIndex;
			}
			if (byteIndex < byteLen) {
				// 中間点より前にある。
				index2 = currentIndex;
			}
		}
		
		// 中途半端なバイトなら偽を返す。
		return false;
	}
	
	/**
	 * インデックスが文字列の範囲外かを判定する。<br/>
	 * @param relativeIndex （相対）インデックス。
	 * @return 引数インデックスが範囲外かの真偽値。（true：範囲外／false：範囲内）
	 */
	public boolean isIndexOutOfBounds(int relativeIndex) {
		if (relativeIndex < 0) {
			return true;
		}
		int byteLen = this.value.getBytes(this.charset).length;
		if (byteLen == 0 && "".equals(this.value)) {
			return true;
		}
		if (byteLen <= relativeIndex) {
			return true;
		}
		return false;
	}
	
	/**
	 * 指定バイトインデックスから文字列を切り出す。<br/>
	 * 切り出し後の文字列について、指定インデックスのバイトを含む。<br/>
	 * @param relativeStartIndex 切り出しの開始バイトインデックス。
	 * @return 文字列切り出し後のオブジェクト。
	 */
	public ByteUnitString substring(int relativeStartIndex) throws IndexOutOfBoundsException {
		// 相対的な終了インデックス（＝バイト長）を取得する。
		int relativeEndIndex = this.length;
		return substring(relativeStartIndex, relativeEndIndex);
	}
	
	/**
	 * 指定バイトインデックスから文字列を切り出す。<br/>
	 * 切り出し後の文字列について、指定インデックスの開始バイトを含むが、
	 * 指定インデックスの終了バイトは含まない。
	 * @param relativeStartIndex 切り出しの開始バイトインデックス。
	 * @param relativeEndIndex 切り出しの終了バイトインデックス。
	 * @return 文字列切り出し後のオブジェクト。
	 */
	public ByteUnitString substring(int relativeStartIndex, int relativeEndIndex) throws IndexOutOfBoundsException {
		// 引数のrelativeStartIndexは相対的なインデックス。
		int byteLength = relativeEndIndex - relativeStartIndex;
		if (this.length < byteLength) {
			throw new IndexOutOfBoundsException();
		}
		
		ByteUnitString newObj = this.clone();
		
		newObj.setStartIndex(relativeStartIndex);
		newObj.setLength(byteLength);
		newObj.refreshValue();
		return newObj;
	}
	
	/**
	 * 文字列の開始インデックスを設定する。<br/>
	 * @param relativeIndex 設定する開始バイトインデックス。
	 * @return 設定後のオブジェクト。
	 */
	private ByteUnitString setStartIndex(int relativeIndex) throws IndexOutOfBoundsException {
		if (isIndexOutOfBounds(relativeIndex)) {
			throw new IndexOutOfBoundsException();
		}
		this.startIndex += relativeIndex;
		this.length -= relativeIndex;
		this.refreshValue();
		return this;
	}
	
	/**
	 * 文字列のバイト長を設定する。<br/>
	 * @param byteLength 設定するバイト長。
	 * @return 設定後のオブジェクト。
	 */
	private ByteUnitString setLength(int byteLength) throws IndexOutOfBoundsException {
		if (byteLength == 0) {
			// ゼロの場合だけ、インデックス計算がずれるので固定設定。
			this.length = 0;
			this.refreshValue();
			return this;
		}
		
		// 最終インデックスが範囲内かを確認。
		int tempEndIndex = byteLength - 1;
		if (isIndexOutOfBounds(tempEndIndex)) {
			throw new IndexOutOfBoundsException();
		}
		// バイト長を設定。
		this.length = byteLength;
		this.refreshValue();
		return this;
	}
	
	/**
	 * 現在の文字列の終了インデックスを取得する。<br>
	 * @return 現在の文字列の終了インデックス。 
	 */
	public int getEndIndex() {
		int absoluteEndIndex = this.startIndex + this.length;
		return absoluteEndIndex;
	}
	
	/**
	 * 指定インデックスの次のきりのいいインデックスを取得する。
	 * @param relativeIndex 相対インデックス。
	 * @return 次のきりのいいインデックス。
	 */
	public int nextProperIndex(int relativeIndex) {
		if (isIndexOutOfBounds(relativeIndex)) {
			throw new IndexOutOfBoundsException();
		}
		
		int absoluteIndex = this.startIndex + relativeIndex;
		// 引数インデックス以降のきりのいい文字のインデックス。
		int lastIndex = this.startIndex + this.length;
		for(int tmpIdx = absoluteIndex+1; tmpIdx < lastIndex; ++tmpIdx) {
			// original内の「index」がきりがいいのか判定。 
			if (this.isProperIndex(tmpIdx)) {
				// index以降できりのいい文字のインデックス。
				return tmpIdx;
			}
		}
		
		// index以降できりのいい文字のインデックスは無し。
		return -1;
	}
	
	/**
	 * 指定インデックスの前のきりのいいインデックスを取得する。
	 * @param relativeIndex 相対インデックス。
	 * @return 前のきりのいいインデックス。
	 */
	public int previousProperIndex(int relativeIndex) {
		if (isIndexOutOfBounds(relativeIndex)) {
			throw new IndexOutOfBoundsException();
		}
		
		int absoluteIndex = this.startIndex + relativeIndex;
		// 引数インデックス以降のきりのいい文字のインデックス。
		for(int tmpIdx = absoluteIndex - 1; this.startIndex - 1 < tmpIdx; --tmpIdx) {
			// original内の「index」がきりがいいのか判定。 
			if (isProperIndex(tmpIdx)) {
				// index以前できりのいい文字のインデックス。
				return tmpIdx;
			}
		}
		
		// index以降できりのいい文字のインデックスは無し。
		return -1;
	}
	
	/**
	 * StartIndexがきりのいいインデックスを差しているかの真偽値を返す。
	 * @return きりのいいインデックスを差しているかの真偽値。
	 */
	public boolean isProperStartIndex() {
		// origin内の「StartIndex」がきりがいいのか判定。 
		if (isProperIndex(this.startIndex)) {
			// きりがいい。
			return true;
		}
		
		// きりが悪い。
		return false;
	}
	
	/**
	 * EndIndexがきりのいいインデックスを差しているかの真偽値を返す。
	 * @return きりのいいインデックスを差しているかの真偽値。
	 */
	public boolean isProperEndIndex() {
		// このメソッドは要テスト。
		// 最終文字がきりのいい文字か。
		
		int absoluteEndIndex = getEndIndex();
		int originByteLength = this.original.getBytes(this.charset).length;
		if (absoluteEndIndex == -1 || absoluteEndIndex == originByteLength) {
			// きりがいい。
			return true;
		}
		// 「endIndex」がきりがいいのか確認。 
		if (isProperIndex(absoluteEndIndex)) {
			// きりがいい。
			return true;
		}
		
		// きりが悪い。
		return false;
	}
	
	/**
	 * 半端なバイトをトリムする。<br/>
	 * @return 半端バイトのトリム処理後のオブジェクト。
	 */
	public ByteUnitString trimOddByte() {
		trimLeftOddByte();
		trimRightOddByte();
		return this;
	}
	
	/**
	 * 半端なバイトを左トリムする。<br/>
	 * @return 左トリム処理後のオブジェクト。
	 */
	public ByteUnitString trimLeftOddByte() {
		if (this.isProperStartIndex()) {
			// トリムの必要なし。
			this.refreshValue();
			return this;
		}
		
		// トリムの処理。
		int nextIndx = nextProperIndex(0);
		if (nextIndx == -1) {
			this.length = 0;
			this.startIndex = this.original.getBytes(this.charset).length - 1; // 微妙
		} else {
			// 開始indexを変更し、終了indexは変更しないため、長さを調整する。
			int subByte = nextIndx - this.startIndex;
			setLength(this.length - subByte);
			this.startIndex = nextIndx;
		}
		
		this.refreshValue();
		return this;
	}
	
	/**
	 * 半端なバイトを右トリムする。<br/>
	 * @return 右トリム処理後のオブジェクト。
	 */
	public ByteUnitString trimRightOddByte() {
		if (this.isProperEndIndex()) {
			// トリムの必要なし。
			this.refreshValue();
			return this;
		}
		
		// トリムの処理。
		// int endIndx = getEndIndex();
		int endIndx = this.startIndex + this.length - 1;
		int lastIndex = this.original.getBytes(charset).length - 1;
		if (endIndx == lastIndex) {
			// トリムの必要なし。
			this.refreshValue();
			return this;
		}
		for (int index = endIndx; index > this.startIndex; --index) {
			//     カレント（絶）＋１がきりがいいならカレント（絶）まで切り出す
			if (isProperIndex(index+1)) {
				this.setStartIndex(0);
				this.setLength(index + 1 - this.startIndex);
				this.refreshValue();
				return this;
			}
			
			// Start（絶）なら空白を返す。
		}
		this.setLength(0);
		this.refreshValue();
		return this;
	}
	
	/**
	 * バイト長を返す。<br/>
	 * @return 文字列のバイト長。
	 */
	public int getByteLength() {
		this.refreshValue();
		return this.length;
	}
	
	/**
	 * 文字数を返す。<br/>
	 * @return 文字列の文字数。
	 */
	public int getCharLength() {
		this.refreshValue();
		return this.value.length();
	}
	
	@Override
	public ByteUnitString clone() {
		ByteUnitString obj = new ByteUnitString(this.original, this.charset);
		obj.startIndex = this.startIndex;
		obj.length = this.length;
		obj.refreshValue();
		return obj;
	}
	
	@Override
	public String toString() {
		this.refreshValue();
		return this.value;
	}
	
	/**
	 * 現在の文字列を保持する変数を更新する。
	 */
	private void refreshValue() {
		this.value = this.getString();
	}
	
	/**
	 * 現在の文字列を取得する。
	 * @return 現在の文字列。
	 */
	private String getString() {
		int copyLength = this.length;
		byte[] srcData = this.original.getBytes(this.charset);
		byte[] destData = new byte[this.length];
		
		System.arraycopy(srcData, this.startIndex, destData, 0, copyLength);
		String string = new String(destData, this.charset);
		return string;
	}
	
	/**
	 * substring等で切り出しされた文字列を初期時の状態に戻す。<br/>
	 * @return 初期化したときの状態のオブジェクト。
	 */
	public ByteUnitString restore() {
		this.startIndex = 0;
		this.length = this.original.getBytes(this.charset).length;
		this.value = this.original;
		return this;
	}
}
