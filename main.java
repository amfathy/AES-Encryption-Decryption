import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static class AES {
        String RC[] =  { "00000000","00000001","00000010", "00000100" , "00001000" ,  "00010000" , "00100000" , "01000000" , "10000000" ,  "00011011" , "00110110" };
        List<String> Keys = new ArrayList<>();
        int[] Mul02 = {
                0x00,0x02,0x04,0x06,0x08,0x0a,0x0c,0x0e,0x10,0x12,0x14,0x16,0x18,0x1a,0x1c,0x1e,
                0x20,0x22,0x24,0x26,0x28,0x2a,0x2c,0x2e,0x30,0x32,0x34,0x36,0x38,0x3a,0x3c,0x3e,
                0x40,0x42,0x44,0x46,0x48,0x4a,0x4c,0x4e,0x50,0x52,0x54,0x56,0x58,0x5a,0x5c,0x5e,
                0x60,0x62,0x64,0x66,0x68,0x6a,0x6c,0x6e,0x70,0x72,0x74,0x76,0x78,0x7a,0x7c,0x7e,
                0x80,0x82,0x84,0x86,0x88,0x8a,0x8c,0x8e,0x90,0x92,0x94,0x96,0x98,0x9a,0x9c,0x9e,
                0xa0,0xa2,0xa4,0xa6,0xa8,0xaa,0xac,0xae,0xb0,0xb2,0xb4,0xb6,0xb8,0xba,0xbc,0xbe,
                0xc0,0xc2,0xc4,0xc6,0xc8,0xca,0xcc,0xce,0xd0,0xd2,0xd4,0xd6,0xd8,0xda,0xdc,0xde,
                0xe0,0xe2,0xe4,0xe6,0xe8,0xea,0xec,0xee,0xf0,0xf2,0xf4,0xf6,0xf8,0xfa,0xfc,0xfe,
                0x1b,0x19,0x1f,0x1d,0x13,0x11,0x17,0x15,0x0b,0x09,0x0f,0x0d,0x03,0x01,0x07,0x05,
                0x3b,0x39,0x3f,0x3d,0x33,0x31,0x37,0x35,0x2b,0x29,0x2f,0x2d,0x23,0x21,0x27,0x25,
                0x5b,0x59,0x5f,0x5d,0x53,0x51,0x57,0x55,0x4b,0x49,0x4f,0x4d,0x43,0x41,0x47,0x45,
                0x7b,0x79,0x7f,0x7d,0x73,0x71,0x77,0x75,0x6b,0x69,0x6f,0x6d,0x63,0x61,0x67,0x65,
                0x9b,0x99,0x9f,0x9d,0x93,0x91,0x97,0x95,0x8b,0x89,0x8f,0x8d,0x83,0x81,0x87,0x85,
                0xbb,0xb9,0xbf,0xbd,0xb3,0xb1,0xb7,0xb5,0xab,0xa9,0xaf,0xad,0xa3,0xa1,0xa7,0xa5,
                0xdb,0xd9,0xdf,0xdd,0xd3,0xd1,0xd7,0xd5,0xcb,0xc9,0xcf,0xcd,0xc3,0xc1,0xc7,0xc5,
                0xfb,0xf9,0xff,0xfd,0xf3,0xf1,0xf7,0xf5,0xeb,0xe9,0xef,0xed,0xe3,0xe1,0xe7,0xe5
        };
        int[] Mul03 = {
                0x00,0x03,0x06,0x05,0x0c,0x0f,0x0a,0x09,0x18,0x1b,0x1e,0x1d,0x14,0x17,0x12,0x11,
                0x30,0x33,0x36,0x35,0x3c,0x3f,0x3a,0x39,0x28,0x2b,0x2e,0x2d,0x24,0x27,0x22,0x21,
                0x60,0x63,0x66,0x65,0x6c,0x6f,0x6a,0x69,0x78,0x7b,0x7e,0x7d,0x74,0x77,0x72,0x71,
                0x50,0x53,0x56,0x55,0x5c,0x5f,0x5a,0x59,0x48,0x4b,0x4e,0x4d,0x44,0x47,0x42,0x41,
                0xc0,0xc3,0xc6,0xc5,0xcc,0xcf,0xca,0xc9,0xd8,0xdb,0xde,0xdd,0xd4,0xd7,0xd2,0xd1,
                0xf0,0xf3,0xf6,0xf5,0xfc,0xff,0xfa,0xf9,0xe8,0xeb,0xee,0xed,0xe4,0xe7,0xe2,0xe1,
                0xa0,0xa3,0xa6,0xa5,0xac,0xaf,0xaa,0xa9,0xb8,0xbb,0xbe,0xbd,0xb4,0xb7,0xb2,0xb1,
                0x90,0x93,0x96,0x95,0x9c,0x9f,0x9a,0x99,0x88,0x8b,0x8e,0x8d,0x84,0x87,0x82,0x81,
                0x9b,0x98,0x9d,0x9e,0x97,0x94,0x91,0x92,0x83,0x80,0x85,0x86,0x8f,0x8c,0x89,0x8a,
                0xab,0xa8,0xad,0xae,0xa7,0xa4,0xa1,0xa2,0xb3,0xb0,0xb5,0xb6,0xbf,0xbc,0xb9,0xba,
                0xfb,0xf8,0xfd,0xfe,0xf7,0xf4,0xf1,0xf2,0xe3,0xe0,0xe5,0xe6,0xef,0xec,0xe9,0xea,
                0xcb,0xc8,0xcd,0xce,0xc7,0xc4,0xc1,0xc2,0xd3,0xd0,0xd5,0xd6,0xdf,0xdc,0xd9,0xda,
                0x5b,0x58,0x5d,0x5e,0x57,0x54,0x51,0x52,0x43,0x40,0x45,0x46,0x4f,0x4c,0x49,0x4a,
                0x6b,0x68,0x6d,0x6e,0x67,0x64,0x61,0x62,0x73,0x70,0x75,0x76,0x7f,0x7c,0x79,0x7a,
                0x3b,0x38,0x3d,0x3e,0x37,0x34,0x31,0x32,0x23,0x20,0x25,0x26,0x2f,0x2c,0x29,0x2a,
                0x0b,0x08,0x0d,0x0e,0x07,0x04,0x01,0x02,0x13,0x10,0x15,0x16,0x1f,0x1c,0x19,0x1a
        };

        int[] Mul09 = {
                0x00,0x09,0x12,0x1b,0x24,0x2d,0x36,0x3f,0x48,0x41,0x5a,0x53,0x6c,0x65,0x7e,0x77,
                0x90,0x99,0x82,0x8b,0xb4,0xbd,0xa6,0xaf,0xd8,0xd1,0xca,0xc3,0xfc,0xf5,0xee,0xe7,
                0x3b,0x32,0x29,0x20,0x1f,0x16,0x0d,0x04,0x73,0x7a,0x61,0x68,0x57,0x5e,0x45,0x4c,
                0xab,0xa2,0xb9,0xb0,0x8f,0x86,0x9d,0x94,0xe3,0xea,0xf1,0xf8,0xc7,0xce,0xd5,0xdc,
                0x76,0x7f,0x64,0x6d,0x52,0x5b,0x40,0x49,0x3e,0x37,0x2c,0x25,0x1a,0x13,0x08,0x01,
                0xe6,0xef,0xf4,0xfd,0xc2,0xcb,0xd0,0xd9,0xae,0xa7,0xbc,0xb5,0x8a,0x83,0x98,0x91,
                0x4d,0x44,0x5f,0x56,0x69,0x60,0x7b,0x72,0x05,0x0c,0x17,0x1e,0x21,0x28,0x33,0x3a,
                0xdd,0xd4,0xcf,0xc6,0xf9,0xf0,0xeb,0xe2,0x95,0x9c,0x87,0x8e,0xb1,0xb8,0xa3,0xaa,
                0xec,0xe5,0xfe,0xf7,0xc8,0xc1,0xda,0xd3,0xa4,0xad,0xb6,0xbf,0x80,0x89,0x92,0x9b,
                0x7c,0x75,0x6e,0x67,0x58,0x51,0x4a,0x43,0x34,0x3d,0x26,0x2f,0x10,0x19,0x02,0x0b,
                0xd7,0xde,0xc5,0xcc,0xf3,0xfa,0xe1,0xe8,0x9f,0x96,0x8d,0x84,0xbb,0xb2,0xa9,0xa0,
                0x47,0x4e,0x55,0x5c,0x63,0x6a,0x71,0x78,0x0f,0x06,0x1d,0x14,0x2b,0x22,0x39,0x30,
                0x9a,0x93,0x88,0x81,0xbe,0xb7,0xac,0xa5,0xd2,0xdb,0xc0,0xc9,0xf6,0xff,0xe4,0xed,
                0x0a,0x03,0x18,0x11,0x2e,0x27,0x3c,0x35,0x42,0x4b,0x50,0x59,0x66,0x6f,0x74,0x7d,
                0xa1,0xa8,0xb3,0xba,0x85,0x8c,0x97,0x9e,0xe9,0xe0,0xfb,0xf2,0xcd,0xc4,0xdf,0xd6,
                0x31,0x38,0x23,0x2a,0x15,0x1c,0x07,0x0e,0x79,0x70,0x6b,0x62,0x5d,0x54,0x4f,0x46

        };
        int[] Mul11 = {
                0x00,0x0b,0x16,0x1d,0x2c,0x27,0x3a,0x31,0x58,0x53,0x4e,0x45,0x74,0x7f,0x62,0x69,
                0xb0,0xbb,0xa6,0xad,0x9c,0x97,0x8a,0x81,0xe8,0xe3,0xfe,0xf5,0xc4,0xcf,0xd2,0xd9,
                0x7b,0x70,0x6d,0x66,0x57,0x5c,0x41,0x4a,0x23,0x28,0x35,0x3e,0x0f,0x04,0x19,0x12,
                0xcb,0xc0,0xdd,0xd6,0xe7,0xec,0xf1,0xfa,0x93,0x98,0x85,0x8e,0xbf,0xb4,0xa9,0xa2,
                0xf6,0xfd,0xe0,0xeb,0xda,0xd1,0xcc,0xc7,0xae,0xa5,0xb8,0xb3,0x82,0x89,0x94,0x9f,
                0x46,0x4d,0x50,0x5b,0x6a,0x61,0x7c,0x77,0x1e,0x15,0x08,0x03,0x32,0x39,0x24,0x2f,
                0x8d,0x86,0x9b,0x90,0xa1,0xaa,0xb7,0xbc,0xd5,0xde,0xc3,0xc8,0xf9,0xf2,0xef,0xe4,
                0x3d,0x36,0x2b,0x20,0x11,0x1a,0x07,0x0c,0x65,0x6e,0x73,0x78,0x49,0x42,0x5f,0x54,
                0xf7,0xfc,0xe1,0xea,0xdb,0xd0,0xcd,0xc6,0xaf,0xa4,0xb9,0xb2,0x83,0x88,0x95,0x9e,
                0x47,0x4c,0x51,0x5a,0x6b,0x60,0x7d,0x76,0x1f,0x14,0x09,0x02,0x33,0x38,0x25,0x2e,
                0x8c,0x87,0x9a,0x91,0xa0,0xab,0xb6,0xbd,0xd4,0xdf,0xc2,0xc9,0xf8,0xf3,0xee,0xe5,
                0x3c,0x37,0x2a,0x21,0x10,0x1b,0x06,0x0d,0x64,0x6f,0x72,0x79,0x48,0x43,0x5e,0x55,
                0x01,0x0a,0x17,0x1c,0x2d,0x26,0x3b,0x30,0x59,0x52,0x4f,0x44,0x75,0x7e,0x63,0x68,
                0xb1,0xba,0xa7,0xac,0x9d,0x96,0x8b,0x80,0xe9,0xe2,0xff,0xf4,0xc5,0xce,0xd3,0xd8,
                0x7a,0x71,0x6c,0x67,0x56,0x5d,0x40,0x4b,0x22,0x29,0x34,0x3f,0x0e,0x05,0x18,0x13,
                0xca,0xc1,0xdc,0xd7,0xe6,0xed,0xf0,0xfb,0x92,0x99,0x84,0x8f,0xbe,0xb5,0xa8,0xa3
        };

        int[] Mul13 = {
                0x00,0x0d,0x1a,0x17,0x34,0x39,0x2e,0x23,0x68,0x65,0x72,0x7f,0x5c,0x51,0x46,0x4b,
                0xd0,0xdd,0xca,0xc7,0xe4,0xe9,0xfe,0xf3,0xb8,0xb5,0xa2,0xaf,0x8c,0x81,0x96,0x9b,
                0xbb,0xb6,0xa1,0xac,0x8f,0x82,0x95,0x98,0xd3,0xde,0xc9,0xc4,0xe7,0xea,0xfd,0xf0,
                0x6b,0x66,0x71,0x7c,0x5f,0x52,0x45,0x48,0x03,0x0e,0x19,0x14,0x37,0x3a,0x2d,0x20,
                0x6d,0x60,0x77,0x7a,0x59,0x54,0x43,0x4e,0x05,0x08,0x1f,0x12,0x31,0x3c,0x2b,0x26,
                0xbd,0xb0,0xa7,0xaa,0x89,0x84,0x93,0x9e,0xd5,0xd8,0xcf,0xc2,0xe1,0xec,0xfb,0xf6,
                0xd6,0xdb,0xcc,0xc1,0xe2,0xef,0xf8,0xf5,0xbe,0xb3,0xa4,0xa9,0x8a,0x87,0x90,0x9d,
                0x06,0x0b,0x1c,0x11,0x32,0x3f,0x28,0x25,0x6e,0x63,0x74,0x79,0x5a,0x57,0x40,0x4d,
                0xda,0xd7,0xc0,0xcd,0xee,0xe3,0xf4,0xf9,0xb2,0xbf,0xa8,0xa5,0x86,0x8b,0x9c,0x91,
                0x0a,0x07,0x10,0x1d,0x3e,0x33,0x24,0x29,0x62,0x6f,0x78,0x75,0x56,0x5b,0x4c,0x41,
                0x61,0x6c,0x7b,0x76,0x55,0x58,0x4f,0x42,0x09,0x04,0x13,0x1e,0x3d,0x30,0x27,0x2a,
                0xb1,0xbc,0xab,0xa6,0x85,0x88,0x9f,0x92,0xd9,0xd4,0xc3,0xce,0xed,0xe0,0xf7,0xfa,
                0xb7,0xba,0xad,0xa0,0x83,0x8e,0x99,0x94,0xdf,0xd2,0xc5,0xc8,0xeb,0xe6,0xf1,0xfc,
                0x67,0x6a,0x7d,0x70,0x53,0x5e,0x49,0x44,0x0f,0x02,0x15,0x18,0x3b,0x36,0x21,0x2c,
                0x0c,0x01,0x16,0x1b,0x38,0x35,0x22,0x2f,0x64,0x69,0x7e,0x73,0x50,0x5d,0x4a,0x47,
                0xdc,0xd1,0xc6,0xcb,0xe8,0xe5,0xf2,0xff,0xb4,0xb9,0xae,0xa3,0x80,0x8d,0x9a,0x97
        };
        int[] Mul14 = {
                0x00,0x0e,0x1c,0x12,0x38,0x36,0x24,0x2a,0x70,0x7e,0x6c,0x62,0x48,0x46,0x54,0x5a,
                0xe0,0xee,0xfc,0xf2,0xd8,0xd6,0xc4,0xca,0x90,0x9e,0x8c,0x82,0xa8,0xa6,0xb4,0xba,
                0xdb,0xd5,0xc7,0xc9,0xe3,0xed,0xff,0xf1,0xab,0xa5,0xb7,0xb9,0x93,0x9d,0x8f,0x81,
                0x3b,0x35,0x27,0x29,0x03,0x0d,0x1f,0x11,0x4b,0x45,0x57,0x59,0x73,0x7d,0x6f,0x61,
                0xad,0xa3,0xb1,0xbf,0x95,0x9b,0x89,0x87,0xdd,0xd3,0xc1,0xcf,0xe5,0xeb,0xf9,0xf7,
                0x4d,0x43,0x51,0x5f,0x75,0x7b,0x69,0x67,0x3d,0x33,0x21,0x2f,0x05,0x0b,0x19,0x17,
                0x76,0x78,0x6a,0x64,0x4e,0x40,0x52,0x5c,0x06,0x08,0x1a,0x14,0x3e,0x30,0x22,0x2c,
                0x96,0x98,0x8a,0x84,0xae,0xa0,0xb2,0xbc,0xe6,0xe8,0xfa,0xf4,0xde,0xd0,0xc2,0xcc,
                0x41,0x4f,0x5d,0x53,0x79,0x77,0x65,0x6b,0x31,0x3f,0x2d,0x23,0x09,0x07,0x15,0x1b,
                0xa1,0xaf,0xbd,0xb3,0x99,0x97,0x85,0x8b,0xd1,0xdf,0xcd,0xc3,0xe9,0xe7,0xf5,0xfb,
                0x9a,0x94,0x86,0x88,0xa2,0xac,0xbe,0xb0,0xea,0xe4,0xf6,0xf8,0xd2,0xdc,0xce,0xc0,
                0x7a,0x74,0x66,0x68,0x42,0x4c,0x5e,0x50,0x0a,0x04,0x16,0x18,0x32,0x3c,0x2e,0x20,
                0xec,0xe2,0xf0,0xfe,0xd4,0xda,0xc8,0xc6,0x9c,0x92,0x80,0x8e,0xa4,0xaa,0xb8,0xb6,
                0x0c,0x02,0x10,0x1e,0x34,0x3a,0x28,0x26,0x7c,0x72,0x60,0x6e,0x44,0x4a,0x58,0x56,
                0x37,0x39,0x2b,0x25,0x0f,0x01,0x13,0x1d,0x47,0x49,0x5b,0x55,0x7f,0x71,0x63,0x6d,
                0xd7,0xd9,0xcb,0xc5,0xef,0xe1,0xf3,0xfd,0xa7,0xa9,0xbb,0xb5,0x9f,0x91,0x83,0x8d
        };



        int[][] S_BOX =
                {
                    {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f,
                            0xc5, 0x30, 0x01,0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
                    {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47,0xf0,
                            0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
                    {0xb7,0xfd,0x93,0x26,0x36,0x3f,0xf7,
                            0xcc,0x34,0xa5,0xe5,0xf1,0x71,0xd8,0x31,0x15},
                    {0x04,0xc7,0x23,0xc3,0x18,0x96,0x05,
                            0x9a,0x07,0x12,0x80,0xe2,0xeb,0x27,0xb2,0x75},
                    {0x09,0x83,0x2c,0x1a,0x1b,0x6e,0x5a,0xa0,0x52
                            ,0x3b,0xd6,0xb3,0x29,0xe3,0x2f,0x84},
                    {0x53,0xd1,0x00,0xed,0x20,0xfc,0xb1,
                            0x5b,0x6a,0xcb,0xbe,0x39,0x4a,0x4c,0x58,0xcf},
                    {0xd0,0xef,0xaa,0xfd,0x43,0x4d,0x33,0x85,0x45,
                            0xf9, 0x02,0x7f,0x50,0x3c,0x9f,0xa8},
                    {0x51,0xa3,0x40,0x8f,0x92,0x9d,0x38,0xf5,
                            0xbc,0xb6,0xda,0x21,0x10,0xff,0xf3,0xd2},
                    {0xcd,0x0c,0x13,0xec,0x5f,0x97,0x44,0x17,
                            0xc4,0xa7,0x7e,0x3d,0x64,0x5d,0x19,0x73},
                    {0x60,0x81,0x4f,0xdc,0x22,0x2a,0x90,0x88,
                            0x46,0xee,0xb8,0x14,0xde,0x5e,0x0b,0xdb},
                    {0xe0,0x32,0x3a,0x0a,0x49,0x06,0x24,
                            0x5c,0xc2,0xd3,0xac,0x62,0x91,0x95,0xe4	,0x79},
                    {0xe7,0xc8,0x37,0x6d,0x8d,0xd5,0x4e,0xa9,
                            0x6c,0x56,0xf4,0xea,0x65,0x7a,0xae,0x08},
                    {0xba,0x78,0x25,0x2e,0x1c,0xa6,0xb4,
                            0xc6,0xe8,0xdd,0x74,0x1f,0x4b,0xbd,0x8b,0x8a},
                    {0x70,0x3e,0xb5,0x66,0x48,0x03,0xf6,0x0e,
                            0x61,0x35,0x57,0xb9,0x86,0xc1,0x1d,0x9e},
                    {0xe1,0xf8,0x98,0x11,0x69,0xd9,0x8e,0x94,0x9b
                            ,0x1e,0x87,0xe9,0xce,0x55,0x28,0xdf},
                    {0x8c,0xa1,0x89,0x0d,0xbf,0xe6,0x42,0x68
                            ,0x41,0x99,0x2d,0x0f,0xb0,0x54,0xbb,0x16}
                };


        int[][] S_BOX_INV =
                {
                    { 0x52 , 0x09 , 0x6a , 0xd5 , 0x30 , 0x36 , 0xa5 , 0x38 , 0xbf , 0x40 , 0xa3 , 0x9e , 0x81 , 0xf3 , 0xd7 , 0xfb },
                    { 0x7c , 0xe3 , 0x39 , 0x82 , 0x9b , 0x2f , 0xff , 0x87 , 0x34 , 0x8e , 0x43 , 0x44 , 0xc4 , 0xde , 0xe9 , 0xcb },
                    { 0x54 , 0x7b , 0x94 , 0x32 , 0xa6 , 0xc2 , 0x23 , 0x3d , 0xee , 0x4c , 0x95 , 0x0b , 0x42 , 0xfa , 0xc3 , 0x4e },
                    { 0x08 , 0x2e , 0xa1 , 0x66 , 0x28 , 0xd9 , 0x24 , 0xb2 , 0x76 , 0x5b , 0xa2 , 0x49 , 0x6d , 0x8b , 0xd1 , 0x25 },
                    { 0x72 , 0xf8 , 0xf6 , 0x64 , 0x86 , 0x68 , 0x98 , 0x16 , 0xd4 , 0xa4 , 0x5c , 0xcc , 0x5d , 0x65 , 0xb6 , 0x92 },
                    { 0x6c , 0x70 , 0x48 , 0x50 , 0xfd , 0xed , 0xb9 , 0xda , 0x5e , 0x15 , 0x46 , 0x57 , 0xa7 , 0x8d , 0x9d , 0x84 },
                    { 0x90 , 0xd8 , 0xab , 0x00 , 0x8c , 0xbc , 0xd3 , 0x0a , 0xf7 , 0xe4 , 0x58 , 0x05 , 0xb8 , 0xb3 , 0x45 , 0x06 },
                    { 0xd0 , 0x2c , 0x1e , 0x8f , 0xca , 0x3f , 0x0f , 0x02 , 0xc1 , 0xaf , 0xbd , 0x03 , 0x01 , 0x13 , 0x8a , 0x6b },
                    { 0x3a , 0x91 , 0x11 , 0x41 , 0x4f , 0x67 , 0xdc , 0xea , 0x97 , 0xf2 , 0xcf , 0xce , 0xf0 , 0xb4 , 0xe6 , 0x73 },
                    { 0x96 , 0xac , 0x74 , 0x22 , 0xe7 , 0xad , 0x35 , 0x85 , 0xe2 , 0xf9 , 0x37 , 0xe8 , 0x1c , 0x75 , 0xdf , 0x6e },
                    { 0x47 , 0xf1 , 0x1a , 0x71 , 0x1d , 0x29 , 0xc5 , 0x89 , 0x6f , 0xb7 , 0x62 , 0x0e , 0xaa , 0x18 , 0xbe , 0x1b },
                    { 0xfc , 0x56 , 0x3e , 0x4b , 0xc6 , 0xd2 , 0x79 , 0x20 , 0x9a , 0xdb , 0xc0 , 0xfe , 0x78 , 0xcd , 0x5a , 0xf4 },
                    { 0x1f , 0xdd , 0xa8 , 0x33 , 0x88 , 0x07 , 0xc7 , 0x31 , 0xb1 , 0x12 , 0x10 , 0x59 , 0x27 , 0x80 , 0xec , 0x5f },
                    { 0x60 , 0x51 , 0x7f , 0xa9 , 0x19 , 0xb5 , 0x4a , 0x0d , 0x2d , 0xe5 , 0x7a , 0x9f , 0x93 , 0xc9 , 0x9c , 0xef },
                    { 0xa0 , 0xe0 , 0x3b , 0x4d , 0xae , 0x2a , 0xf5 , 0xb0 , 0xc8 , 0xeb , 0xbb , 0x3c , 0x83 , 0x53 , 0x99 , 0x61 },
                    { 0x17 , 0x2b , 0x04 , 0x7e , 0xba , 0x77 , 0xd6 , 0x26 , 0xe1 , 0x69 , 0x14 , 0x63 , 0x55 , 0x21 , 0x0c , 0x7d },
                };



        List<StringBuilder> plainToBinary(String input) { // Hello RAHF
            List<StringBuilder> blocks = new ArrayList<>();
            StringBuilder result = new StringBuilder();
            char[] chars = input.toCharArray();
            for (char aChar : chars) {
                result.append(
                        String.format("%8s", Integer.toBinaryString(aChar))
                                .replaceAll(" ", "0")
                );
            }
            if (result.length() != 128) {
                for (String s : generateBlocks(result.toString())) {
                    blocks.add(new StringBuilder(s));
                }
            } else
                blocks.add(result);
            return blocks;
        }

        String BinToString(StringBuilder cipher){
            String str = "";

            for (int i = 0; i < cipher.length()/8; i++) {

                int a = Integer.parseInt(cipher.substring(8*i,(i+1)*8),2);
                str += (char)(a);
            }
            return str;
        }


        List<String> generateBlocks(String binary) {
            List<String> result = new ArrayList<>();
            int index = 0;
            while (index < binary.length()) {
                String res = binary.substring(index, Math.min(index + 128, binary.length())); // 0, min(64,128)

                int size = 128 - res.length();
                StringBuilder temp = new StringBuilder();
                while (temp.length() < size) {
                    temp.append('0');
                }

                result.add(temp + res);
                index += 128;
            }
            return result;
        }


    public String hexToBin(String s) {
            String hex = "";
            for(int i=0; i<s.length();i++){
                BigInteger bigInteger = new BigInteger(String.valueOf(s.charAt(i)), 16);
                if(bigInteger.toString(2).length() == 4){
                    hex+= bigInteger.toString(2);
                }else if(bigInteger.toString(2).length() == 3){
                    hex+= "0" + bigInteger.toString(2);
                }else if(bigInteger.toString(2).length() == 2){
                    hex+= "00" + bigInteger.toString(2);
                }else{
                    hex+= "000" + bigInteger.toString(2);
                }

            }
        return hex;
    }
//    public  String BinToHex(StringBuilder b){ return  Integer.toString(Integer.parseInt(String.valueOf(b),2),16);}

    StringBuilder XOR(StringBuilder text, String text2) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < 128; i++) {
            output.append(text.charAt(i) ^ text2.charAt(i));
        }
        return output;
    }
    StringBuilder XOR_8(StringBuilder text, String text2) {
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < 8; i++) {
                output.append(text.charAt(i) ^ text2.charAt(i));
            }
            return output;
        }
    StringBuilder XOR_32(StringBuilder text, String text2) {
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < 32; i++) {
                output.append(text.charAt(i) ^ text2.charAt(i));
            }
            return output;
        }
        StringBuilder sboxIndexGeneration(String Byte){

            StringBuilder result=new StringBuilder();
            StringBuilder SI= new StringBuilder();
            String row="";
            String col="";

        row = Byte.substring(0, 4);
        col = Byte.substring(4);
        result.append(searchSbox(row, col));
        return result;
    }

    String sboxIndexGenerationINV(String Byte){

        String result="";
        StringBuilder SI= new StringBuilder();
        String row="";
        String col="";

        row = Byte.substring(0, 4);
        col = Byte.substring(4);
        result+=searchSboxINV(row,col);
        return result;
    }

        StringBuilder searchSbox(String row, String col){
        int sboxDecimal = S_BOX[Integer.parseInt(row, 2)][Integer.parseInt(col, 2)];
        String sboxBinary = Integer.toBinaryString(sboxDecimal);
        int size = 8 - sboxBinary.length();
            StringBuilder expandedBin = new StringBuilder();
        while (expandedBin.length()<size) {
            expandedBin.append('0');
        }
        expandedBin.append(sboxBinary);
        return expandedBin;
    }

    String searchSboxINV(String row, String col){
        int sboxDecimal = S_BOX_INV[Integer.parseInt(row, 2)][Integer.parseInt(col, 2)];
        String sboxBinary = Integer.toBinaryString(sboxDecimal);
        int size = 8 - sboxBinary.length();
        String expandedBin="";
        while (expandedBin.length()<size) {
            expandedBin+='0';
        }
        expandedBin+=sboxBinary;
        return expandedBin;
    }


        public StringBuilder[][] convertArray(List<StringBuilder> B){
            int iterator = 0;
            StringBuilder [][] B_Matrix= new StringBuilder[4][4];
            StringBuilder[][] transpose = new StringBuilder[4][4];


            // Convert 1d array into 2d matrix
            for(int row = 0; row < 4; row++)
            {
                for(int col = 0; col < 4; col++)
                {
                    B_Matrix[row][col] = B.get(iterator);
                    iterator++;
                }
            }

            // Transpose 2d matrix
            for(int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    transpose[j][i] = B_Matrix[i][j];
                }
            }
            return transpose;
        }


        public StringBuilder[][] shiftMatrixByK(StringBuilder [][]mat) {
            for(int row = 0; row<4; row++){

                // Shift K(row) times
                for(int j=0; j<row; j++){
                    StringBuilder temp = mat[row][0];
                    for(int i=0;i<3;i++)
                    {
                        mat[row][i]=mat[row][i+1];
                    }
                    mat[row][3]=temp;
                }
            }
            return mat;
        }

        public StringBuilder[][] shiftMatrixByKINV(StringBuilder[][] mat) {
            for(int row = 0; row<4; row++){

                // Shift K times
                for(int j=0; j<row; j++){
                    StringBuilder temp = mat[row][3];
                    for(int i=3;i>0;i--)
                    {
                        mat[row][i]=mat[row][i-1];
                    }
                    mat[row][0]=temp;
                }
            }
            return mat;
        }


        String StringToBinary(int text) { // Hello ash
            String binaryST = Integer.toBinaryString(text);
            int size = 8 - binaryST.length();
            String expandedBin="";
            while (expandedBin.length()<size) {
                expandedBin+='0';
            }
            expandedBin+=binaryST;


            return expandedBin;
        }


    // Encrypt
    ArrayList<StringBuilder> ByteSubstitution(StringBuilder input){
        String block = "";
        ArrayList<StringBuilder> B = new ArrayList<>();
        for(int i=0; i<128; i++){
            if(i%8!=0 || i == 0){
                block+=input.charAt(i);
                if(i==127){
                    B.add(sboxIndexGeneration(block));
                }
            }else{
                B.add(sboxIndexGeneration(block));
                block="";
                block+=input.charAt(i);
            }
        }
        return B;
    }

    public ArrayList<String> ByteSubstitutionINV(StringBuilder input) {
        String block = "";
        ArrayList<String> B = new ArrayList<>();
        for(int i=0; i<128; i++){
            if(i%8!=0 || i == 0){
                block+=input.charAt(i);
                if(i==127){
                    B.add(sboxIndexGenerationINV(block));
                }
            }else{
                B.add(sboxIndexGenerationINV(block));
                block="";
                block+=input.charAt(i);
            }
        }
        return B;
    }




        public List<StringBuilder> StringToList(StringBuilder keyAddition) {
            List<StringBuilder> Keyadditions = new ArrayList<>();
            for (int i =0; i<16; i++){
                Keyadditions.add(new StringBuilder(keyAddition.substring(i * 8, i * 8 + 8)));
            }
            return Keyadditions;
        }

        public StringBuilder ListToString(List<String> list) {
             StringBuilder combinedList = new StringBuilder();
            for (int i =0; i<16; i++){
                combinedList.append(list.get(i));
            }
            return combinedList;
        }

    public StringBuilder[][] mixColumns(StringBuilder[][] b_matrix) {
        String[][] mixColumnsMatrix = new String[][]
                {
                    {"02", "03", "01", "01"},
                    {"01", "02", "03", "01"},
                    {"01", "01", "02", "03"},
                    {"03", "01", "01", "02"},
                };
        StringBuilder[][] mixedMatrix = new StringBuilder[4][4];
        StringBuilder result = new StringBuilder("00000000");
        for(int k = 0 ; k<4; k++){
            for (int i=0 ; i<4; i++){
                for (int j=0 ; j<4; j++){
                    if(mixColumnsMatrix[i][j] == "03"){
                        result = XOR_8(result, StringToBinary(Mul03[Integer.parseInt(String.valueOf(b_matrix[j][k]),2)]));
                    }else if(mixColumnsMatrix[i][j] == "02"){
                        result = XOR_8(result, StringToBinary(Mul02[Integer.parseInt(String.valueOf(b_matrix[j][k]),2)]));

                    }else if(mixColumnsMatrix[i][j] == "01") {
                        result = XOR_8(result, String.valueOf(b_matrix[j][k]));
                    }
                }
                mixedMatrix[i][k] = result;
            }
        }

        return mixedMatrix;
    }

        public StringBuilder[][] mixColumnsINV(StringBuilder[][] b_matrix) {
            String[][] mixColumnsMatrix = new String[][]
                    {
                            {"0E", "0B", "0D", "09"},
                            {"09", "0E", "0B", "0D"},
                            {"0D", "09", "0E", "0B"},
                            {"0B", "0D", "09", "0E"},
                    };

            StringBuilder[][] mixedMatrix = new StringBuilder[4][4];
            StringBuilder result;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    result = new StringBuilder("00000000");

                    for (int k = 0; k < 4; k++) {
                        StringBuilder temp = new StringBuilder(b_matrix[k][j]);

                        // Perform multiplication based on the mixColumnsMatrix
                        if (mixColumnsMatrix[i][k].equals("09")) {
                            temp = new StringBuilder(StringToBinary(Mul09[Integer.parseInt(String.valueOf(temp), 2)]));
                        } else if (mixColumnsMatrix[i][k].equals("0B")) {
                            temp = new StringBuilder(StringToBinary(Mul11[Integer.parseInt(String.valueOf(temp), 2)]));
                        } else if (mixColumnsMatrix[i][k].equals("0D")) {
                            temp = new StringBuilder(StringToBinary(Mul13[Integer.parseInt(String.valueOf(temp), 2)]));
                        } else if (mixColumnsMatrix[i][k].equals("0E")) {
                            temp = new StringBuilder(StringToBinary(Mul14[Integer.parseInt(String.valueOf(temp), 2)]));
                        }

                        // XOR the results
                        result = XOR_8(result, String.valueOf(temp));
                    }

                    // Assign the result to the corresponding position in the mixedMatrix
                    mixedMatrix[i][j] = result;
                }
            }

            return mixedMatrix;
        }




        public String Gfunction(String s,int round) {
            String[] wordByteV= new String[4];
            for (int i=0; i<4; i++){
                wordByteV[i] = s.substring(i*8,i*8+8);
            }

            // Shift left
            for(int j=0; j<4; j++){
                String temp = wordByteV[0];
                for(int i=0;i<3;i++)
                {
                    wordByteV[i]=wordByteV[i+1];
                }
                wordByteV[3]=temp;
            }

            // Sbox search
            String[] S = new String[4];
            for (int i =0; i<4; i++){
                S[i] = String.valueOf(sboxIndexGeneration(wordByteV[i]));
            }

            StringBuilder S0 = new StringBuilder(S[0]);
            // XORing RC
            S0 = XOR_8(S0 ,RC[round]);

            return S0 + S[1] + S[2] + S[3];
        }

        public String combineMatrix(StringBuilder [][] mixColumns) {
            String combinedMatrix = "";
            //   Combine matrix
            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){
                    combinedMatrix += mixColumns[j][i];
                }
            }
            return combinedMatrix;
        }
        public StringBuilder combineMatrixSB(StringBuilder [][] mixColumns) {
            StringBuilder combinedMatrix = new StringBuilder();
            //   Combine matrix
            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){
                    combinedMatrix.append(mixColumns[j][i]);
                }
            }
            return combinedMatrix;
        }

        String keySchedule(String key,int round){
            String[] wordByte= new String[4];
            String W3 = "";
            for (int i=0; i<4; i++){
                wordByte[i] = key.substring(i*32,i*32+32);
            }

            // G-function
            W3 = Gfunction(wordByte[3],round);

            // XORING

            StringBuilder W4,W5,W6,W7 = new StringBuilder();
            W4 = XOR_32(new StringBuilder(W3),wordByte[0]);
            W5 = XOR_32(W4, wordByte[1]);
            W6 = XOR_32(W5, wordByte[2]);
            W7 = XOR_32(W6, wordByte[3]);

            return W4 +""+ W5 +""+ W6 +""+ W7;
        }
        StringBuilder mergeBlocks(List<StringBuilder> cipherBlocks){
            StringBuilder result = new StringBuilder();
            for (StringBuilder S : cipherBlocks){
                result.append(S);
            }
            return result;
        }


        // Encryption

        List<StringBuilder> encrypt(List<StringBuilder> input, String key) {
            List<StringBuilder> Cipher = new ArrayList<>();
            StringBuilder keyAddition = new StringBuilder();
            StringBuilder combinedMatrix = new StringBuilder();
            String keyScheduled = "";
            StringBuilder [][] B_Matrix;
            ArrayList<StringBuilder> ByteSubstitution;
            for (StringBuilder S : input){
                for (int i=0; i<11; i++){
                    if(i==0){
                        keyAddition = XOR(S,key);
                        keyScheduled = key;
                    }else if(i==10){
                        ByteSubstitution = ByteSubstitution(keyAddition);
                        B_Matrix = convertArray(ByteSubstitution);
                        B_Matrix = shiftMatrixByK(B_Matrix);
                        combinedMatrix = combineMatrixSB(B_Matrix);
                        keyScheduled = keySchedule(keyScheduled,i);
                        keyAddition = XOR(new StringBuilder(combinedMatrix),keyScheduled);
                    }
                    else{
                        ByteSubstitution = ByteSubstitution(keyAddition);
                        B_Matrix = convertArray(ByteSubstitution);
                        B_Matrix = shiftMatrixByK(B_Matrix);
                        combinedMatrix = new StringBuilder(combineMatrix(mixColumns(B_Matrix)));
                        keyScheduled = keySchedule(keyScheduled,i);
                        keyAddition = XOR(new StringBuilder(combinedMatrix),keyScheduled);
                    }
                    if(Keys.size()!=11){
                        Keys.add(keyScheduled);
                    }
                }
                Cipher.add(keyAddition);
        }
        return Cipher;
    }

    List<StringBuilder> decrypt(List<StringBuilder> cipherBlocks){
            StringBuilder combinedList = new StringBuilder();
            List<StringBuilder> ListKeyAddition = new ArrayList<>();
            StringBuilder keyAddition = new StringBuilder();
            StringBuilder[][] mixedColumns = new StringBuilder[4][4];
            List<StringBuilder> cipherText = cipherBlocks;
            StringBuilder combinedMatrix = new StringBuilder();
            List<StringBuilder> CipherText = new ArrayList<>();
            StringBuilder [][] B_Matrix= new StringBuilder[4][4];
            ArrayList<String> ByteSubstitution= new ArrayList<>();
            for (StringBuilder S : cipherBlocks){
                for (int i=10; i>=0; i--){
                    if(i==0){
                        combinedList = ListToString(ByteSubstitution);
                        keyAddition = XOR(combinedList,Keys.get(i));
                    }else if(i==10){
                        keyAddition = XOR(S,Keys.get(i));
                        ListKeyAddition = StringToList(keyAddition);
                        B_Matrix = convertArray(ListKeyAddition);
                        B_Matrix = shiftMatrixByKINV(B_Matrix);
                        combinedMatrix = combineMatrixSB(B_Matrix);
                        ByteSubstitution = ByteSubstitutionINV(new StringBuilder(combinedMatrix));
                    }
                    else{
                        combinedList = ListToString(ByteSubstitution);
                        keyAddition = XOR(combinedList,Keys.get(i));
                        B_Matrix = convertArray(StringToList(keyAddition));
                        mixedColumns = mixColumnsINV(B_Matrix);
                        B_Matrix = shiftMatrixByKINV(mixedColumns);
                        combinedMatrix = combineMatrixSB(B_Matrix);
                        ByteSubstitution = ByteSubstitutionINV(new StringBuilder(combinedMatrix));
                    }
                }

                CipherText.add(keyAddition);



            }




        return cipherText;
    }


        public static void main(String[] args) {
        AES obj = new AES();
        System.out.println("Enter the text");
        Scanner scanner = new Scanner(System.in);
        List<StringBuilder> blocks = new ArrayList<>();
        String input = scanner.nextLine();
        blocks = obj.plainToBinary(input);
        int keySize;
        String key="";
        while (true) {
            System.out.println("Enter 32 HEX key: ");
            key = scanner.nextLine();
            keySize=key.length();
            key = obj.hexToBin(key);
            if (keySize != 32) {
                System.out.println("Error! Insert a new key");
            }else
                break;
        }

        StringBuilder EncryptedBinary = obj.mergeBlocks(obj.encrypt(blocks,key));

        System.out.println("Binary Text : ");
        System.out.println(obj.mergeBlocks(blocks));;
        System.out.println("");

        System.out.println("Binary Ciphered Text : ");
        System.out.println(EncryptedBinary);;
        System.out.println("");

        System.out.println("String Ciphered Text : ");
        System.out.println(obj.BinToString(EncryptedBinary));
        System.out.println("");

        StringBuilder DecryptedBinary = obj.mergeBlocks(obj.decrypt(blocks));

        System.out.println("Binary cecipher Text : ");
        System.out.println(DecryptedBinary);
        System.out.println("");


        }
    }
} // 2b7e151628aed2a6abf7158809cf4f3c
