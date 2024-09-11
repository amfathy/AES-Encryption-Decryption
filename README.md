# AES Encryption and Decryption

This repository contains an implementation of the Advanced Encryption Standard (AES) in Java. The code includes functionality for both encryption and decryption using AES. 

## Features

- Converts plain text to binary and vice versa.
- Encrypts and decrypts text using AES with a 32-character hexadecimal key.
- Implements key scheduling, Byte Substitution, and Mix Columns transformations.
- Supports both encryption and decryption processes.

## Code Overview

### `plainToBinary(String input)`

Converts plain text to a binary representation.

**Parameters:**
- `input`: Plain text string to be converted.

**Returns:**
- A list of `StringBuilder` objects representing binary blocks.

### `BinToString(StringBuilder cipher)`

Converts binary data to a string.

**Parameters:**
- `cipher`: Binary data as `StringBuilder`.

**Returns:**
- The decoded string.

### `hexToBin(String s)`

Converts a hexadecimal string to its binary representation.

**Parameters:**
- `s`: Hexadecimal string.

**Returns:**
- Binary string.

### `XOR(StringBuilder text, String text2)`

Performs a bitwise XOR operation on two binary strings.

**Parameters:**
- `text`: First binary string.
- `text2`: Second binary string.

**Returns:**
- XOR result as `StringBuilder`.

### `ByteSubstitution(StringBuilder input)`

Applies Byte Substitution transformation on the input binary data.

**Parameters:**
- `input`: Binary data as `StringBuilder`.

**Returns:**
- List of transformed `StringBuilder` blocks.

### `encrypt(List<StringBuilder> input, String key)`

Encrypts the input binary data using AES with the provided key.

**Parameters:**
- `input`: List of binary data blocks.
- `key`: 32-character hexadecimal key.

**Returns:**
- List of encrypted `StringBuilder` blocks.

### `decrypt(List<StringBuilder> cipherBlocks)`

Decrypts the input cipher blocks using AES.

**Parameters:**
- `cipherBlocks`: List of encrypted `StringBuilder` blocks.

**Returns:**
- List of decrypted `StringBuilder` blocks.

## Usage

1. Clone the repository:
   ```sh
   git clone https://github.com/amfathy/aes-encryption-java.git
