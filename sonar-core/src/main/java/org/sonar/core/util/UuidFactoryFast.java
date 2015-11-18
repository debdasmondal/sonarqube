/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.core.util;

/**
 * NOT thread safe
 * About 10x faster than {@link UuidFactoryImpl}
 */
public class UuidFactoryFast implements UuidFactory {
  private static UuidFactoryFast instance = new UuidFactoryFast();
  private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  private static int sequenceNumber = 0;

  private UuidFactoryFast() {
    //
  }

  @Override
  public String create() {
    long timestamp = System.currentTimeMillis();

    byte[] uuidBytes = new byte[9];

    // Only use lower 6 bytes of the timestamp (this will suffice beyond the year 10000):
    putLong(uuidBytes, timestamp, 0, 6);

    // Sequence number adds 3 bytes:
    putLong(uuidBytes, getSequenceNumber(), 6, 3);

    return byteArrayToHex(uuidBytes);
  }

  public static UuidFactoryFast getInstance() {
    return instance;
  }
  
  private static int getSequenceNumber() {
    return sequenceNumber++;
  }

  /** Puts the lower numberOfLongBytes from l into the array, starting index pos. */
  private static void putLong(byte[] array, long l, int pos, int numberOfLongBytes) {
    for (int i = 0; i < numberOfLongBytes; ++i) {
      array[pos + numberOfLongBytes - i - 1] = (byte) (l >>> (i * 8));
    }
  }

  public static String byteArrayToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
  }

}